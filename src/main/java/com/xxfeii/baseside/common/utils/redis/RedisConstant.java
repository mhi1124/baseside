package com.xxfeii.baseside.common.utils.redis;

import com.xxfeii.baseside.common.utils.ProUtil;

/**
 * redis常量
 * @author 
 * @create 2016年11月1日
 */
public class RedisConstant {

	/**
	 * ;分隔符
	 */
	public static final String SEPARATION_SIGN1 = ";";
	
	/**
	 * :分隔符
	 */
	public static final String SEPARATION_SIGN2 = ":";
	
	/**
	 * redis 缓存地址 地址加端口 格式 地址：端口 多台时以“;”号分割
	 */
	public static final String REDIS_HOSTS=ProUtil.getConfig("redis.hosts");

	/**
	 * 密码
	 */
	public static final String REDIS_PASSWORD=ProUtil.getConfig("redis.password");
	/**
	 * 数据库,默认0
	 */
	public static final int REDIS_DATABASE=ProUtil.getIntConfig("redis.database");
	/**
	 * 最大闲置
	 */
	public static final int REDIS_POOL_MAXIDLE=ProUtil.getIntConfig("redis.pool.maxIdle");
	/**
	 * 最小闲置
	 */
	public static final int REDIS_POOL_MINIDLE=ProUtil.getIntConfig("redis.pool.minIdle");
	public static final boolean REDIS_POOL_TESTONBORROW=ProUtil.getBooleanConfig("redis.pool.testOnBorrow");
	public static final boolean REDIS_POOL_TESTONRETURN=ProUtil.getBooleanConfig("redis.pool.testOnReturn");
	/**
	 * 超时
	 */
	public static final int REDIS_TIMEOUT=ProUtil.getIntConfig("redis.timeout");
	/**
	 * sentinel 部署方式 start
	 * sentinel 
	 */
	public static final String REDIS_MASTER=ProUtil.getConfig("redis.master");
	/**
	 * 观察地址  地址：端口号 多个以“;”分割
	 */
	public static final String REDIS_SENTINELS=ProUtil.getConfig("redis.sentinels");
	
	/**
	 * 等待时间
	 */
	public static final int REDIS_POOL_MAXWAIT=ProUtil.getIntConfig("redis.pool.maxWait");
}
