package com.befriend.wechat;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import com.befriend.util.OpeFunction;

public class RefreshAccessToken {
	public static String access_token ="YWMtkvqI-mJpEeWmHWM9KfQewAAAAVEyULKkcoThtUNYEYm9zyNKr60dHfj3bjc";
	public static int Second=0;
	public final static  String url = "https://a1.easemob.com/topLong/wcfriend/token";
	public RefreshAccessToken(int second) {
		Second=second;
		//
		Timer timer = new Timer();
		// ִ���� �ӳ�ʱ��(����) ÿ����� ��*1000(����)
		timer.schedule(new RefreshAccessTokenTask(), 0, Second * 1000);

	}

	public class RefreshAccessTokenTask extends TimerTask {

		@Override
		public void run() {

			// ΢��
			// url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WechatAction.APPID+"&secret="+WechatAction.APPSECRET;
			// ����
			
			try {
				
				// ��װ��json  ��ȡtoken��
				JSONObject json = new JSONObject();
//				json.put("client_id", "YXA6MHPlMALGEeWyhu9dNjM0XA");
//				json.put("grant_type", "client_credentials");
//				json.put("client_secret", "YXA6OhW1URHjHtAbX_R0ocltSfiWNLQ");
				
				
				
				
				 //json.put("username", "topLong");
				 //json.put("password", "toplong!");

				// ��ȡ��json access_token ΢�ŵ�
				// access_token=OpeFunction.getJsonKey(WechatKit.sendGet(url),
				// "access_token");
			 System.out.println("access_token:"+access_token);
				System.out.println("��װ��json:"+json.toString());

				String jn = null;
				
				jn=WechatKit.post(url, json,null);
				 System.out.println("jn:"+jn);
				
				if (jn != null) {
					if( OpeFunction.getJsonKey(jn, "access_token")!=null){
					// ��ȡaccess_token
					access_token = OpeFunction.getJsonKey(jn, "access_token");
					}
				}
				System.out.println("access_token:"+access_token);

			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			System.out.println("ÿ��"+Second+"sִ��" + OpeFunction.getNowTime());

		}

	}

}
