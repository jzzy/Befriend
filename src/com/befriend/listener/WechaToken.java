package com.befriend.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.befriend.wechat.RefreshAccessToken;
public class WechaToken  implements ServletContextListener{
	public final static int Second=432000;//����Ϊ��λ

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("�ر�WechaTolen");
		
		
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
			/**
			 * ��ʼ ���� ��ȡ token 432000��  6day
			 */
			System.out.println("��ʼWechaTolen....");
			new RefreshAccessToken(Second);
			System.out.println("ÿ��"+Second+"�� ִ��һ��....");
			
		
	}


}
