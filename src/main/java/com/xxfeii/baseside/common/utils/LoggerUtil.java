package com.xxfeii.baseside.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日记工具类
 * 
 * @author 
 * @create 2016年11月1日
 */
public class LoggerUtil {

	/**
	 * 一般的日记信息
	 */
	private static Log myInfo = LogFactory.getLog("myInfo");

	/**
	 * 错误信息
	 */
	private static Log myError = LogFactory.getLog("myError");

	/**
	 * 记录一般信息
	 * @param info
	 */
	public static void writeMyInfo(String info) {
		myInfo.info(info);
	}

	/**
	 * 记录错误信息
	 * @param info
	 */
	public static void writeMyError(String info) {
		myError.info(info);
	}

	/**
	 * 把堆栈错误信息记录
	 * @param e
	 */
	public static void writeError(Exception e){
		StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw =  new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        writeMyError(sw.toString());
	}
	
}
