package com.xxfeii.baseside.modules.sys.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * 
 * @ClassName SerSocket
 * @Description 多线程socket
 * @author davi
 * @date 2016年8月30日
 *
 */
public class SerSocket implements Runnable {

	private Socket socket;

	public SerSocket(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		receiveData();
	}

	/**
	 * 数据处理
	 */
	private void receiveData() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "utf-8"));
			int len = 0;
			String temp = "";
			StringBuilder sb = new StringBuilder();
			while ((temp = br.readLine()) != null) {
				if ((len = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
					sb.append(temp.substring(0, len));
					break;
				}
				sb.append(temp);
			}
			System.out.println("客户端: " + sb);
			Writer writer = new OutputStreamWriter(socket.getOutputStream(),
					"UTF-8");
			writer.write("你好，客户端。");
			writer.write("eof\n");
			writer.flush();
			writer.close();
			br.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
