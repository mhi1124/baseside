package com.xxfeii.baseside.modules.sys.controller;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.common.servlet.ValidateCodeServlet;
import com.xxfeii.baseside.common.utils.ImageUtil;
import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.exception.SystemException;
import com.xxfeii.baseside.modules.sys.service.MenuService;
import com.xxfeii.baseside.modules.sys.utils.AjaxUtil;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.encrypt.RSAUtils;
import com.xxfeii.baseside.util.shiro.ShiroUtil;

@Controller
@Scope("prototype")
@RequestMapping("/sys")
public class LoginController extends BaseController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "login.html", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String login(HttpServletRequest request, HttpServletResponse resp) {
		try {
			request.removeAttribute("error");
			return Constant.BACK_PATH+"/modules/sys/login";
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 登录验证
	 * 
	 * @param req
	 * @return
	 */

	@RequestMapping(value = "login.html", method = RequestMethod.POST)
	public String toIndex(HttpServletRequest req) {
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		String captcha = req.getParameter("captcha");
		// 获取用户页面输入的验证码
		if (!ValidateCodeServlet.validate(req, captcha)) {
			req.setAttribute("error", "验证码错误！");
			return Constant.BACK_PATH+"/modules/sys/login";
		} else {
			// 根据模和私钥指数
			String modulus = (String) req.getSession().getAttribute("Modulus");
			String private_exponent = (String) req.getSession().getAttribute("private_exponent");
			if (StringUtils.isNotBlank(modulus) && StringUtils.isNotBlank(private_exponent)) {
				password = RSAUtils.encode(password, modulus, private_exponent);
			}
			UsernamePasswordToken token = null;
			try {
				// 想要得到Subject对象,访问地址必须在shiro的拦截地址内,不然会报空指针
				Subject subject = SecurityUtils.getSubject();
				token = new UsernamePasswordToken(accountName, password);
				subject.login(token);
				if (subject.isAuthenticated()) {
					req.removeAttribute("error");
				} else {
					token.clear();
					req.setAttribute("error", "用户名或密码不正确！");
					return Constant.BACK_PATH+"/modules/sys/login";
				}
			} catch (UnknownAccountException uae) {
				if (null != token) {
					token.clear();
				}
				System.out.println("账户不存在！");
				req.setAttribute("error", "账户不存在！");
				return Constant.BACK_PATH+"/modules/sys/login";
			} catch (IncorrectCredentialsException ice) {
				if (null != token) {
					token.clear();
				}
				System.out.println("密码错误！");
				req.setAttribute("error", "密码错误！");
				return Constant.BACK_PATH+"/modules/sys/login";
			} catch (LockedAccountException e) {
				if (null != token) {
					token.clear();
				}
				System.out.println("您的账户已被锁定,请与管理员联系或10分钟后重试！");
				req.setAttribute("error", "您的账户已被锁定,请与管理员联系或10分钟后重试！");
				return Constant.BACK_PATH+"/modules/sys/login";
			} catch (ExcessiveAttemptsException e) {
				if (null != token) {
					token.clear();
				}
				System.out.println("您连续输错5次,帐号将被锁定10分钟!");
				req.setAttribute("error", "您连续输错5次,帐号将被锁定10分钟!");
				return Constant.BACK_PATH+"/modules/sys/login";
			} catch (ExpiredCredentialsException eca) {
				if (null != token) {
					token.clear();
				}
				System.out.println("账户凭证过期！");
				req.setAttribute("error", "账户凭证过期！");
				return Constant.BACK_PATH+"/modules/sys/login";
			} catch (AuthenticationException e) {
				if (null != token) {
					token.clear();
				}
				System.out.println("账户验证失败！");
				req.setAttribute("error", "账户验证失败！");
				return Constant.BACK_PATH+"/modules/sys/login";
			} catch (Exception e) {
				if (null != token) {
					token.clear();
				}
				System.out.println("登录异常，请联系管理员！");
				req.setAttribute("error", "登录异常，请联系管理员！");
				return Constant.BACK_PATH+"/modules/sys/login";
			}
		}
		return "redirect:/sys/index.html";
	}

	/**
	 * 进入首页
	 * 
	 * @param session
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "index.html")
	public String index(HttpSession session, HttpServletRequest req) {
		// 获取登录的bean
		User user = ShiroUtil.getUser();
		// User user = (User)session.getAttribute(Constant.USE_RSESSION_ID);
		if (null != user) {
			List<Menu> menus = menuService.findMenuByAccountName(user.getAccountName());
			req.setAttribute("menus", menus);
			return Constant.BACK_PATH+"/modules/sys/index";
		} else {
			return "redirect:/sys/login.html";
		}
	}

	/**
	 * 生成加密公钥
	 * 
	 * @param req
	 * @param resp
	 */
	@RequestMapping("loginset.html")
	public void tloginset(HttpServletRequest req, HttpServletResponse resp) {
		RSAUtils rsa = new RSAUtils();
		// 生成公钥和密钥
		Map<String, Object> keyMap = rsa.createKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get("publicKey");
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get("privateKey");
		// js通过模和公钥指数获取公钥对字符串进行加密，注意必须转为16进制
		// 模
		String Modulus = publicKey.getModulus().toString(16);
		// 公钥指数
		String Exponent = publicKey.getPublicExponent().toString(16);
		// 私钥指数
		String private_exponent = privateKey.getPrivateExponent().toString();
		HttpSession session = req.getSession();
		// java中的模和私钥指数不需要转16进制，但是js中的需要转换为16进制
		session.setAttribute("Modulus", publicKey.getModulus().toString());
		session.setAttribute("private_exponent", private_exponent);
		String strSet = Modulus + ";" + Exponent;
		AjaxUtil.sendData(resp, strSet);
	}

	@RequestMapping(value = "captcha.html", method = RequestMethod.GET)
	public void kaptcha(HttpServletRequest req, HttpServletResponse rsp) {
		ImageUtil ImageUtil = new ImageUtil();
		try {
			ImageUtil.createImage(req, rsp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
