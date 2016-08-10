package com.xxfeii.baseside.modules.sys.listener;


import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xxfeii.baseside.modules.sys.task.TestTask;
import com.xxfeii.baseside.modules.sys.utils.DateUtil;

public class TaskListener implements ServletContextListener{

	private Timer timer = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		timer = new Timer(true);
		
		Date date = DateUtil.getDate(23,35);
		if(date.before(new Date())){
			date = DateUtil.addDay(date, 1);
		}
		timer.scheduleAtFixedRate(new TestTask(), date, DateUtil.ONEDAY);
	}

}
