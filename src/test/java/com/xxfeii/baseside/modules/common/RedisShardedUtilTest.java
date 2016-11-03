package com.xxfeii.baseside.modules.common;

import org.junit.Test;

import com.xxfeii.baseside.common.utils.redis.RedisShardedUtil;


public class RedisShardedUtilTest extends BaseTest{

	
	@Test
	public void getTest(){
		String ss = RedisShardedUtil.get("key001");
		System.out.println(ss);
	}
	
	@Test
	public void setTest(){
		RedisShardedUtil.set("key001","aaaa");
	}
	
	@Test
	public void existsTest(){
		boolean ss = RedisShardedUtil.exists("key001");
		System.out.println(ss);
	}
}
