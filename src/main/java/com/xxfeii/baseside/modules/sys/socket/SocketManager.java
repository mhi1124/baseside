package com.xxfeii.baseside.modules.sys.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName SocketManager
 * @Description Socket 服务器
 * @author davi
 * @date 2016年8月30日
 * 
 */
public class SocketManager implements Runnable {

	private static Logger log = LoggerFactory.getLogger(SocketManager.class);
	/**
	 * 
	 */
	public static Map<String, Socket> SocketMap = new HashMap<String, Socket>();

	@Override
	public void run() {
		doListen();
	}

	private void doListen() {
		String ip = "";
		ServerSocket server;
		try {
			server = new ServerSocket(8000);
			while (true) {
				log.info("监听8000端口~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Socket client = server.accept();
				log.info("监听8000端口~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + client);
				ip = client.getInetAddress().getHostAddress();
				if (client != null) {
					SocketMap.put(ip, client);
				}
				log.info("阻塞监听" + client.getInetAddress().getHostAddress());
				Thread thread = new Thread(new SerSocket(client));
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new SocketManager());
		thread.start();
	}
}
