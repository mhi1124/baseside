package com.xxfeii.baseside.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxfeii.baseside.common.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping("login/")
public class LoginController extends BaseController{

	@RequestMapping("index")
	public String toIndex(HttpServletRequest req){
		return "index";
	}
}
