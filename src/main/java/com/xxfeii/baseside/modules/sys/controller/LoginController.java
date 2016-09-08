package com.xxfeii.baseside.modules.sys.controller;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxfeii.baseside.common.controller.BaseController;
import com.xxfeii.baseside.modules.sys.utils.AjaxUtil;
import com.xxfeii.baseside.modules.sys.utils.encrypt.RSAUtils;

@Controller
@Scope("prototype")
@RequestMapping("/login/")
public class LoginController extends BaseController{

	@RequestMapping("index")
	public String toIndex(HttpServletRequest req){
		String loginName = req.getParameter("loginName");
		String loginPwd = req.getParameter("subPwd");
		System.out.println(loginPwd);
		RSAUtils rsa = new RSAUtils();
		String Modulus = (String) req.getSession().getAttribute("Modulus");
		String private_exponent = (String)req.getSession().getAttribute("private_exponent");
		//根据模和私钥指数获取私钥
		RSAPrivateKey prkey = RSAUtils.getPrivateKey(Modulus,private_exponent);
		try {
			//解密
			String password = rsa.decrypttoStr(prkey,loginPwd);
			System.out.println(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("loginset")
	public void loginset(HttpServletRequest req,HttpServletResponse resp){
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
