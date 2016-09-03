package com.xxfeii.baseside.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ProUtil {
	/**
	 * 当前对象实例
	 */
	private static ProUtil proUtil = new ProUtil();
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader(
			"jdbc.properties", "es.properties");

	/**
	 * 获取当前对象实例
	 */
	public static ProUtil getInstance() {
		return proUtil;
	}

	/**
	 * 获取配置
	 * 
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
			if (StringUtils.isEmpty(value)) {
				String path = getConfig("es_mappin_json");
				if(StringUtils.isNotEmpty(path)){
					Map<String, String> jsons = JsonUtil.readJsonDefn(path);
					value = jsons.get(key);
					map.putAll(jsons);
				}
			}
		}
		return value;
	}

	/**
	 * 获取配置文件中的整数类型的数;不是数字类型的值返回0
	 * 
	 * @param key
	 * @return
	 */
	public static int getIntConfig(String key) {
		try {
			return Integer.valueOf(getConfig(key));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		String es_host = getConfig("es_host");
		System.out.println(es_host);
	}
}
