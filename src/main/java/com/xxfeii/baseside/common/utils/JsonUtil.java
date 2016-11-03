package com.xxfeii.baseside.common.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import net.sf.json.JSONObject;

public class JsonUtil {
	private static Logger log = LoggerFactory.getLogger(JsonUtil.class);
	
	/**
	 * 实体数据转成json字符串
	 * @param object
	 * @return
	 */
	public static String modelToJson(Object object){
    	String jsonString = "";
		try {
			jsonString = JSON.toJSONString(object);
		} catch (Exception e) {
			log.error("modelToJson error "+e.getMessage());
			return null;
		} 
    	return jsonString ;
    }
	
	/**
	 * 阿里的json解析 对象转字符串
	 * @param object
	 * @return
	 */
	public static String fastModelToJson(Object object){
		String jsonString = "";
		try {
			jsonString = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			log.error("modelToJson error "+e.getMessage());
			return null;
		} 
    	return jsonString ;
	}
	
	/**
	 * null值转为""
	 * @param object
	 * @return
	 */
	public static String fastModelToJsoneEmpty(Object object){
		String jsonString = "";
		ValueFilter valueFilter = new ValueFilter() {
			@Override
			public Object process(Object object, String name, Object value) {
				if (value == null)
					return "";
				return value;
			}
		};
		try {
			jsonString = JSON.toJSONString(object, valueFilter);
		} catch (Exception e) {
			log.error("modelToJson error "+e.getMessage());
			return null;
		} 
    	return jsonString ;
	}
	
	
	/**
	 * 保留空值 实体数据转成json字符串
	 * @param object
	 * @return
	 */
	public static String netModelToJson(Object object){
		 JSONObject jobject = JSONObject.fromObject(object);
		 return jobject.toString();
	}
	
	/**
	 * json字符串转类型
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToModel(String json,Class<T> clazz){
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			log.error("jsonToModel error "+e.getMessage());
			return null;
		}
	}
	
	/**
	 * json转map
	 * @param json
	 * @return
	 */
	public static Map<String,Object> jsonToMap(String json){
		try {
			return JSON.parseObject(json);
		} catch (Exception e) {
			log.error("jsonToModel error "+e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 校验json字符串
	 * @param json
	 * @return
	 */
	public static boolean isJSONValid(String json) {
		try {
			JsonParser parser = new JsonParser();
			JsonElement  elem = parser.parse(json);
			System.out.println(elem.toString());
			if(elem.isJsonNull()){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			log.error("isJSONValid error "+e.getMessage());
			return false;
		}
	}
	
	/**

	 * 获取配置文件设置的索引

	 * 

	 * @param url

	 * @return

	 */
	public static Map<String, String> readJsonDefn(String url) {
		List<File> files = FileUtil.getFileList(url);
		Map<String, String> jsonMap = new HashMap<String, String>();
		for (File f : files) {
			StringBuffer bufferJSON = new StringBuffer();
			FileInputStream input;
			try {
				input = new FileInputStream(f.getAbsolutePath());
				DataInputStream inputStream = new DataInputStream(input);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						inputStream));
				String line;
				while ((line = br.readLine()) != null) {
					bufferJSON.append(line);
				}
				br.close();
			} catch (FileNotFoundException e) {
				log.error("readJsonDefn error " + e.getMessage());
				return null;
			} catch (IOException e) {
				log.error("readJsonDefn error " + e.getMessage());
				return null;
			}
			if (isJSONValid(bufferJSON.toString())) {
				jsonMap.put(FileUtil.getFileName(f), bufferJSON.toString());
			}
		}
		return jsonMap;
	}
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", null);
		map.put("2", "fdsfd");
		String s = netModelToJson(map);
		String s1 = fastModelToJson(map);
		System.out.println(jsonToModel(s1,Map.class));
		System.out.println(s1);
	}
}
