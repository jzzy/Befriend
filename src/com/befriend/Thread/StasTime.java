package com.befriend.Thread;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.befriend.util.OpeFunction;
import com.befriend.wechat.WechatKit;

public class StasTime {
	public StasTime(int second) {
		//
		Timer timer = new Timer();
		// 执行类 延迟时间(毫秒) 每隔多久 秒*1000(毫秒)
		timer.schedule(new RefreshAccessTokenTask(), 0, second * 1000);

	}

	public class RefreshAccessTokenTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			String url = "http://127.0.0.1/Befriend/aStas";

			// Thread.sleep(5000);
			try {
				WechatKit.sendGet(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(OpeFunction.getNowTime());
			System.out.println("StasTime定时任务执行了");

		}

	}

}
