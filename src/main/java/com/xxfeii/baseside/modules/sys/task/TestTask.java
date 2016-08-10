package com.xxfeii.baseside.modules.sys.task;

import java.util.TimerTask;

public class TestTask extends TimerTask{

	@Override
	public void run() {
		System.out.println("定时任务！！！！");
	}

}
