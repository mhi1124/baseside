package com.xxfeii.baseside.modules.es.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

public class ToolUtil {
	/**
	 * 获取spring管理的bean
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-context.xml");
		return ctx.getBean(beanName);
	}

	public static void main(String[] args) {
		DruidDataSource druidDataSource = (DruidDataSource) getBean("dataSource");
		System.out.println(druidDataSource.getUrl());
	}
}
