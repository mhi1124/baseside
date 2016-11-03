package com.xxfeii.baseside.util.log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xxfeii.baseside.common.utils.FileUtil;


/**
 * 统计log 读写大文件
 * @author 
 * @create 2016年11月3日
 */
public class StatisticalLog {

	/**
	 * 一条单点消息 "sid":7,"cid":1 
	 */
	private static String rule3 = "\"sid\":7,\"cid\":1";
	/**
	 * 代表一条群聊消息 "sid":8,"cid":2 
	 */
	private static String rule4 = "\"sid\":8,\"cid\":2";
	
	String rule1 = "\"sid\":2,\"cid\":2";
	String rule2 = "\"sid\":2,\"cid\":4";
	
	/**
	 * 读取log文件获取过滤数据写入文件
	 * @param logPath log文件夹或文件
	 * @param writerPath 写入的文件
	 * @param flag 1表示根据时间汇总，2表示根据时间线程汇总
	 */
	private static void readLog(String logPath,String writerPath,int flag){
		
		List<File> fileList = FileUtil.getFileList(logPath);
		//需要获取的数据
		Map<String,Integer> data = new HashMap<String,Integer>();
		//写文件
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(new File(writerPath));
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			//每次读的数据
			String line = "";
			//读的行数
			Long num = 0l;
			String outline = "";
			for (File fin : fileList) {
				String fileName = fin.getName();
				BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fin));
				BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// ��5M�Ļ����ȡ�ı��ļ�
				while ((line = reader.readLine()) != null) {
					//获取的数据
					if(flag == 1){
						getSendTimes(data,line);
					}else if(flag == 2){
						getSendLineTimes(data,line);
					}
					num++;
					if(num%1000==0){
						System.out.println(fileName+" 处理行数===>:"+num);
					}
				}
				System.out.println(fileName + " 处理行数===>:"+num);
				fis.close();
				reader.close();
			}
			for(String key : data.keySet()){
				outline = key + "," + data.get(key) + "\t\n";
				bw.write(outline);
			}
			//释放资源
			fos.flush();
			osw.flush();
			bw.flush();
			fos.close();
			osw.close();
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("未找到写入文件");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("编码格式有问题！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读文件异常！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取没分钟发送信息的次数
	 * @param data 保存数据的map
	 * @param line 数据
	 */
	private static void getSendTimes(Map<String,Integer> data,String line){
		if (line.indexOf(rule3) != -1 || line.indexOf(rule4) != -1) {
			//获取一行中的时间
			String time = line.substring(0, line.indexOf(' ', line.indexOf(' ') + 1));
			time = time.substring(0,time.lastIndexOf(":"));
			Integer timeCount = data.get(time);
			if(null != timeCount){
				//次数累加
				timeCount += 1;
				data.put(time, timeCount);
			}else{
				data.put(time, 1);
			}
		}
	}
	
	/**
	 * 获取没分钟发送信息的次数
	 * @param data 保存数据的map
	 * @param line 数据
	 */
	private static void getSendLineTimes(Map<String,Integer> data,String line){
		if (line.indexOf(rule3) != -1 || line.indexOf(rule4) != -1) {
			//获取一行中的时间
			String time = line.substring(0, line.indexOf(' ', line.indexOf(' ') + 1));
			time = time.substring(0,time.lastIndexOf(":"));
			//获取线程
			String sendLine = line.substring(line.indexOf("[")+1, line.indexOf("]"));
			String key = time+","+sendLine;
			Integer timeCount = data.get(key);
			if(null != timeCount){
				//次数累加
				timeCount += 1;
				data.put(key, timeCount);
			}else{
				data.put(key, 1);
			}
		}
	}
	
	
	public static void main(String[] args) {
		int flag = 1;
		String logPath = "E:\\WorkFile\\data\\Transfer\\node2";
		String writerPath = "E:\\WorkFile\\data\\a\\node2\\node2Send"+flag+"Times.txt";
		readLog(logPath,writerPath,flag);
	}

}
