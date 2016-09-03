package com.xxfeii.baseside.modules.es.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxfeii.baseside.common.utils.ProUtil;

public class ESClientUtil {
	static Logger log = LoggerFactory.getLogger(ESClientUtil.class);

	/**
	 * es服务器的host
	 */
	private static final String host = ProUtil.getConfig("es_host");

	/**
	 * es服务器暴露给client的port
	 */
	private static final int port = ProUtil.getIntConfig("es_port");

	/**
	 * 集群名称
	 */
	private static final String cluster_name = ProUtil.getConfig("es_cluster_name");

	/**
	 * 静态,单例...
	 */
	private static Client client;

	/**
	 * 获取es连接器
	 * 
	 * @return
	 */
	public static Client getClient() {
		if (client == null) {
			try {
				// 判断配置文件中是否有设集群名称，如果没有设置使用默认的集群名称
				if (StringUtils.isNotEmpty(cluster_name)) {
					Settings settings = Settings.settingsBuilder()
							.put("cluster.name", cluster_name).build();
					client = TransportClient
							.builder()
							.settings(settings)
							.build()
							.addTransportAddress(
									new InetSocketTransportAddress(InetAddress
											.getByName(host), port));
				} else {
					client = TransportClient
							.builder()
							.build()
							.addTransportAddress(
									new InetSocketTransportAddress(InetAddress
											.getByName(host), port));
				}
			} catch (UnknownHostException e) {
				log.error("creat client error : " + e.getMessage());
			}
			return client;
		} else {
			return client;
		}
	}

	/**
	 * 关闭一个连接
	 */
	public static void closeESClient() {
		if (client != null) {
			client.close();
		}
	}
	
	public static void closeClient(Client c){
		c.close();
	}
}
