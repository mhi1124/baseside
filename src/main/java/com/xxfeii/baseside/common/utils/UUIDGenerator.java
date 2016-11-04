package com.xxfeii.baseside.common.utils;

import java.util.UUID;

/**
 * UUID生成器
 * 
 * @author Administrator
 *
 */
public class UUIDGenerator {
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);
		return temp;
	}

	// 获得指定数量的UUID
	public static String[] getUUID(int number) {
		if (number > 0) {
			String[] ss = new String[number];
			for (int i = 0; i < number; i++) {
				ss[i] = getUUID();
			}
			return ss;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; i++) {
			System.out.println("ss[" + i + "]=====" + ss[i]);
		}
	}
}