package com.xxfeii.baseside.modules.sys.listener;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.bouncycastle.asn1.cms.Time;

import com.xxfeii.baseside.modules.sys.socket.SocketManager;

public class TestListener implements ServletContextListener{

	public void TestSocket(){
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					addconfidtypes();
				} catch (Exception e) {
				}
			}
		} , 1000 * 10, 1000 * 60);
	}
	
	private void addconfidtypes(){
		System.out.println("============"+SocketManager.SocketMap.size());
	}
	
	public void socketManager(){
		Thread t = new Thread(new SocketManager());
		t.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		TestSocket();
		socketManager();
	}
}
