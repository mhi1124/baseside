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
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtil {
	private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * 实体数据转成json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String modelToJson(Object object) {
		String jsonString = "";
		try {
			jsonString = JSON.toJSONString(object);
		} catch (Exception e) {
			log.error("modelToJson error " + e.getMessage());
			return null;
		}
		return jsonString;
	}

	/**
	 * json转成Object
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static Object stringToModel(String json, Class<?> cls) {
		try {
			return JSON.parseObject(json, cls);
		} catch (Exception e) {
			log.error("stringToModel error " + e.getMessage());
			return null;
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

	/**
	 * 校验json字符串
	 * 
	 * @param json
	 * @return
	 */
	public static boolean isJSONValid(String json) {
		try {
			JsonParser parser = new JsonParser();
			JsonElement elem = parser.parse(json);
			if (elem.isJsonNull()) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("isJSONValid error " + e.getMessage());
			return false;
		}
	}

	public static void main(String[] args) {
		Map<String, String> jsons = JsonUtil.readJsonDefn("D:/ES/mappingJson");
		String source = jsons.get("test");
		System.out.println(source);
	}
}
