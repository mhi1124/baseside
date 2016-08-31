package com.xxfeii.baseside.modules.sys.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class SocketClient {

	public void sendDate() {
		String host = "127.0.0.1";
		int port = 8000;
		try {
			Socket client = new Socket(host, port);
			// 建立连接后就可以往服务端写数据了
			Writer writer = new OutputStreamWriter(client.getOutputStream(),"utf-8");
			writer.write("你好，服务端。");
			writer.write("eof\n");
			writer.flush();
			// 写完以后进行读操作
			BufferedReader br = new BufferedReader(new InputStreamReader(
					client.getInputStream(), "UTF-8"));
			// 设置超时间为10秒
			client.setSoTimeout(10 * 1000);
			StringBuffer sb = new StringBuffer();
			String temp;
			int index;
			while ((temp = br.readLine()) != null) {
				if ((index = temp.indexOf("eof")) != -1) {
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			System.out.println("服务端: " + sb);
			writer.close();
			br.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			System.out.println("数据读取超时。");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SocketClient socketClient = new SocketClient();
		socketClient.sendDate();
	}
}
