package com.xxfeii.baseside.common.utils.redis;

import com.xxfeii.baseside.common.utils.LoggerUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 非切片redis工具类
 * @author 
 * @create 2016年11月1日
 */
public class RedisAloneUtil {

	// 非切片连接池
	private static JedisPool jedisPool;
	
	static{
		RedisClient redisClient = new RedisClient();
		jedisPool = redisClient.initialPool();
	}
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisPool, jedis);
		}
		return value;
	}

	/**
	 * 保存
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.set(key, value);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisPool, jedis);
		}
		return;
	}

	/**
	 * 删除缓存
	 * @param key
	 */
	public static void del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisPool, jedis);
		}
		return;
	}
	
	/**
	 * 是否存在
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisPool, jedis);
		}
		return false;
	}
	
}
