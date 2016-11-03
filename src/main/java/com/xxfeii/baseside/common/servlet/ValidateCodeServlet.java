package com.xxfeii.baseside.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.xxfeii.baseside.common.utils.ImageUtil;

/**
 * 
 * @ClassName: 生成随机验证码
 * @Description:
 * @author davi
 * @date 2016年4月20日
 * 
 */
public class ValidateCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2796806663952915165L;

	public ValidateCodeServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public static boolean validate(HttpServletRequest request, String validateCode) {
		String code = (String) request.getSession().getAttribute(ImageUtil.VALIDATE_CODE);
		return validateCode.toUpperCase().equals(code);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String validateCode = request.getParameter(ImageUtil.VALIDATE_CODE); // AJAX验证，成功返回true
		if (StringUtils.isNotBlank(validateCode)) {
			response.getOutputStream().print(validate(request, validateCode) ? "true" : "false");
		} else {
			this.doPost(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ImageUtil ImageUtil = new ImageUtil();
		ImageUtil.createImage(request, response);
	}

}
