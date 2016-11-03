package com.xxfeii.baseside.common.utils.redis;

import com.xxfeii.baseside.common.utils.LoggerUtil;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 分片redis工具类
 * 
 * @author 
 * @create 2016年11月1日
 */
public class RedisShardedUtil {

	// 切片连接池
	private static ShardedJedisPool shardedJedisPool;

	static {
		RedisClient redisClient = new RedisClient();
		shardedJedisPool = redisClient.initialShardedPool();
	}

	/**
	 * 释放
	 * @param pool
	 * @param redis
	 */
	public static void emancipate(ShardedJedisPool pool, ShardedJedis redis) {
		if (redis != null) {
			shardedJedisPool.returnResource(redis);
		}
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			shardedJedisPool.returnBrokenResource(jedis);
		} finally {
			emancipate(shardedJedisPool, jedis);
		}
		return value;
	}

	/**
	 * 保存
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			value = jedis.set(key, value);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			shardedJedisPool.returnBrokenResource(jedis);
		} finally {
			emancipate(shardedJedisPool, jedis);
		}
		return;
	}

	/**
	 * 删除缓存
	 * @param key
	 */
	public static void del(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();

			jedis.del(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			shardedJedisPool.returnBrokenResource(jedis);
		} finally {
			emancipate(shardedJedisPool, jedis);
		}
		return;
	}
	
	public static boolean exists(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			LoggerUtil.writeError(e);
			shardedJedisPool.returnBrokenResource(jedis);
		} finally {
			emancipate(shardedJedisPool, jedis);
		}
		return false;
	}
	
	
}
