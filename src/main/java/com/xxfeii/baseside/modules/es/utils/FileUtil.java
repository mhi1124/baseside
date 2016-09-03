package com.xxfeii.baseside.modules.es.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	private static List<File> fileList = new ArrayList<File>();

	/**
	 * 获取文件夹下的文件
	 * 
	 * @param path
	 * @return
	 */
	public static List<File> getFileList(String strPath) {
		List<File> fileList1 = new ArrayList<File>();
		File dir = new File(strPath);
		File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) { // 判断是文件还是文件夹
					getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
				} else {
					fileList.add(files[i]);
					continue;
				}
			}
		}
		fileList1 = fileList;
		return fileList1;
	}

	/**
	 * 获取文件的文件名，没有后缀
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileName(File file) {
		String name = file.getName();
		String notDeff = name.substring(0, name.lastIndexOf('.'));
		return notDeff;
	}

	public static void main(String[] args) {
		List<File> files = getFileList("E:/WorkFile/前端页面");
		System.out.println(getFileName(files.get(0)));
	}
}
