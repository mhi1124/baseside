package com.xxfeii.baseside.modules.common;

import org.junit.Test;

import com.xxfeii.baseside.common.utils.redis.RedisSentinelUtil;

public class RedisSentinelUtilTest extends BaseTest{

	@Test
	public void getTest(){
		String ss = RedisSentinelUtil.get("key001");
		System.out.println(ss);
	}
	
	@Test
	public void setTest(){
		RedisSentinelUtil.set("key001","aaaa");
	}
	
	@Test
	public void existsTest(){
		boolean ss = RedisSentinelUtil.exists("key001");
		System.out.println(ss);
	}
}
