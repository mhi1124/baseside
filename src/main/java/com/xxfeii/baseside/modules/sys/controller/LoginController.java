package com.xxfeii.baseside.modules.sys.controller;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.modules.sys.entity.Menu;
import com.xxfeii.baseside.modules.sys.entity.User;
import com.xxfeii.baseside.modules.sys.exception.SystemException;
import com.xxfeii.baseside.modules.sys.service.MenuService;
import com.xxfeii.baseside.modules.sys.service.UserService;
import com.xxfeii.baseside.modules.sys.utils.AjaxUtil;
import com.xxfeii.baseside.modules.sys.utils.Constant;
import com.xxfeii.baseside.modules.sys.utils.encrypt.RSAUtils;

@Controller
@Scope("prototype")
@RequestMapping("/")
public class LoginController extends BaseController{

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login.html", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String login(HttpServletRequest request,HttpServletResponse resp) {
		try{
			request.removeAttribute("error");
			return "/login";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	/**
	 * 登录验证
	 * @param req
	 * @return
	 */
	
	@RequestMapping(value="login.html",method=RequestMethod.POST)
	public String toIndex(HttpServletRequest req){
		String loginName = req.getParameter("loginName");
		String password = req.getParameter("password");
		//根据模和私钥指数
		String modulus = (String) req.getSession().getAttribute("Modulus");
		String private_exponent = (String)req.getSession().getAttribute("private_exponent");
		if(StringUtils.isNotBlank(modulus)&&StringUtils.isNotBlank(private_exponent)){
			password = RSAUtils.encode(password, modulus, private_exponent);
		}
		User user = userService.findUserByAccountName(loginName,password);
		if(null != user){
			req.getSession().setAttribute(Constant.USE_RSESSION_ID, user);
			return "redirect:index.html";
		}else{
			return "/login";
		}
		
	}
	
	/**
	 * 进入首页
	 * @param session
	 * @param req
	 * @return
	 */
	@RequestMapping(value="index.html")
	public String index(HttpSession session,HttpServletRequest req){
		User user = (User)session.getAttribute(Constant.USE_RSESSION_ID);
		if(null != user){
			List<Menu> menus = menuService.findMenuByAccountName(user.getAccountName());
			req.setAttribute("menus", menus);
			return "/index";
		}else{
			return "redirect:login.html";
		}
	}
	
	/**
	 * 生成加密公钥
	 * @param req
	 * @param resp
	 */
	@RequestMapping("loginset.html")
	public void tloginset(HttpServletRequest req,HttpServletResponse resp){
		RSAUtils rsa = new RSAUtils();
		//生成公钥和密钥
		Map<String,Object> keyMap = rsa.createKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get("publicKey");
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get("privateKey");
		//js通过模和公钥指数获取公钥对字符串进行加密，注意必须转为16进制
		//模
		String Modulus = publicKey.getModulus().toString(16);
		//公钥指数
		String Exponent = publicKey.getPublicExponent().toString(16);
		//私钥指数    
        String private_exponent = privateKey.getPrivateExponent().toString();
        HttpSession session = req.getSession();
        //java中的模和私钥指数不需要转16进制，但是js中的需要转换为16进制
        session.setAttribute("Modulus", publicKey.getModulus().toString());
        session.setAttribute("private_exponent", private_exponent);
        String strSet = Modulus+";"+Exponent;
        AjaxUtil.sendData(resp, strSet);
	}
}
