package com.xxfeii.baseside.modules.sys.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AjaxUtil {

	public static void sendData(HttpServletResponse resp,String data){
		try {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.write(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
