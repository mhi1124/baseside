package com.xxfeii.baseside.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读文件工具类
 * @ClassName ReadFromFile
 * @Description
 * @author davi
 * @date 2016年8月24日
 *
 */
public class ReadFileTool {

	private static Logger logger = LoggerFactory.getLogger(ReadFileTool.class);
	/**
	 * 按行读取文件
	 * @param path
	 * @param filter
	 * @return
	 */
	public static String readFileByLine(String path,String filter){
		File file = new File(path);
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行："); 
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;  
            int line = 1; 
            while(null != (tempString = reader.readLine())){
            	if(StringUtils.isNotEmpty(filter)){
            		if(tempString.indexOf(filter) != -1){
            			sb.append(tempString).append("\n");
            			line++;
            		}
            	}else{
            		sb.append(tempString).append("\n");
            		line++;
            	}
            }
            System.out.println(path+" 行数为:"+line);
            reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 读大文件
	 * @param path 需要读的文件路径
	 * @param toPath 写的文件路径
	 * @param filter 过滤条件，为空不过滤
	 */
	public static void readBigFile(String path,String toPath,String filter){
		FileInputStream inputStream = null;
		Scanner sc = null;
		String line = "";
		try {
			inputStream = new FileInputStream(path);
			sc = new Scanner(inputStream, "UTF-8");
			FileWriter fileWriter = new FileWriter(toPath);
			//记录过滤的行数
			int num = 1;
			//记录总的行数
			int cnum = 1;
			while(sc.hasNextLine()){
				line = sc.nextLine();
				System.out.println(line);
				if(StringUtils.isNotEmpty(filter)){
					if(line.indexOf(filter)>-1){
						fileWriter.append(line+"\n");
						if(num % 100000 == 0){
							logger.info(path+"已经处理："+num+"/"+cnum);
							//写入文件
							fileWriter.flush();
						}
						num++;
					}
				}else{
					fileWriter.append(line+"\n");
					if(cnum % 100000 == 0){
						logger.info(path+"已经处理："+num+"/"+cnum);
						//写入文件
						fileWriter.flush();
					}
				}
				if(cnum % 100000 == 0){
					logger.info(path+"已经处理："+num+"/"+cnum);
					//写入文件
					fileWriter.flush();
				}
				cnum++;
			}
			logger.info(path+"已经处理："+num+"/"+cnum);
			fileWriter.flush();
			fileWriter.close();
			sc.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			logger.error(path+" 文件不存在！！");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally{
			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("关闭input流出错！！");
				}
			}
			if(null != sc){
				sc.close();
			}
		}
	}
	
	public static void main(String[] args) {
		String path = "E:/log_network.txt";
		String toPath = "E:/log_network.txt.txt";
		readBigFile(path,toPath,"");
		//String s = readFileByLine(toPath, "");
		//System.out.println(s);
	}
}
