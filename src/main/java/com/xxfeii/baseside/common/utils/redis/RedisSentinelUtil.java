package com.xxfeii.baseside.common.utils.redis;

import com.xxfeii.baseside.common.utils.LoggerUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;

/**
 * Sentinel模式缓存工具类
 * @author 
 * @create 2016年11月1日
 */
public class RedisSentinelUtil {

	// sentinel模式 ：心跳机制+投票裁决
	private static JedisSentinelPool jedisSentinelPool;// 切片连接池
	
	static{
		RedisClient redisClient = new RedisClient();
		jedisSentinelPool = redisClient.initJedisSentinelPool();
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();

			value = jedis.get(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisSentinelPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisSentinelPool, jedis);
		}
		return value;
	}

	/**
	 * 保存缓存
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			value = jedis.set(key, value);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisSentinelPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisSentinelPool, jedis);
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
			jedis = jedisSentinelPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisSentinelPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisSentinelPool, jedis);
		}
		return;
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			jedisSentinelPool.returnBrokenResource(jedis);
		} finally {
			RedisClient.emancipate(jedisSentinelPool, jedis);
		}
		return false;
	}
	
	
}
