package com.xxfeii.baseside.modules.common;

import org.junit.Test;

import com.xxfeii.baseside.common.utils.redis.RedisAloneUtil;

public class RedisAloneUtilTest extends BaseTest {

	@Test
	public void getTest(){
		String ss = RedisAloneUtil.get("key001");
		System.out.println(ss);
	}
	
	@Test
	public void setTest(){
		RedisAloneUtil.set("key001","aaaa");
	}
	
	@Test
	public void existsTest(){
		boolean ss = RedisAloneUtil.exists("key001");
		System.out.println(ss);
	}
}
