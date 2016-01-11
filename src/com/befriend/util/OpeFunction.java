package com.befriend.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.bouncycastle.asn1.cms.Time;

import com.befriend.email.MailSenderInfo;
import com.befriend.email.SimpleMailSender;
import com.befriend.entity.User;
import com.befriend.wechat.WechatKit;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.sun.rowset.internal.Row;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 工具类
 * 
 * @author gyn
 *
 */

@SuppressWarnings("all")
public class OpeFunction {
	/**
	 * js返回到html
	 * 
	 * @throws IOException
	 */
	public static void outjS(String URL, String value) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String loginPage = URL;
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\">");
		builder.append("alert('" + value + "');");
		builder.append("window.top.location.href='");
		builder.append(loginPage);
		builder.append("';");
		builder.append("</script>");
		out.print(builder.toString());
	}

	/**
	 * 
	 * 空 返回 true
	 * 
	 * 不空 返回 false
	 * 
	 * @param rstart
	 * @param rend
	 * @return
	 */
	public static boolean isEmpty(String string) {

		if (string != null) {
			if (StringUtils.isEmpty(string.trim())) {
				System.out.println("空");
				return true;
			} else {
				System.out.println("不空");
				return false;
			}
		} else {
			System.out.println("空");
			return true;
		}
	}

	// private static final int FIRST_DAY_OF_WEEK = 0;
	/**
	 * 获取本机ip
	 * 
	 * @throws UnknownHostException
	 */
	public static String getfileName() {
		Calendar cal = Calendar.getInstance();// 使用日历类
		System.out.println("年" + cal.get(Calendar.YEAR));
		System.out.println("月" + cal.get(Calendar.MONTH) + 1);
		System.out.println("天" + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("时" + cal.get(Calendar.HOUR));
		System.out.println("分" + cal.get(Calendar.MINUTE));
		System.out.println("秒" + cal.get(Calendar.SECOND));
		;
		String name = "/" + Integer.valueOf(cal.get(Calendar.YEAR)).toString()
				+ Integer.valueOf(cal.get(Calendar.MONTH) + 1).toString()
				+ Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH)).toString()
				+ Integer.valueOf(cal.get(Calendar.HOUR)).toString()
				+ Integer.valueOf(cal.get(Calendar.MINUTE)).toString()
				+ Integer.valueOf(cal.get(Calendar.SECOND)).toString();
		return name;
	}

	/**
	 * 获取本机ip
	 * 
	 * @throws UnknownHostException
	 */
	public static String getLIP() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		return addr.getHostAddress().toString();
	}

	/**
	 * 通过key 从json中 取值
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static User getJsonKeyUs(String str) {
		Gson gs = new Gson();
		User u = gs.fromJson(str, User.class);
		return u;

	}

	/**
	 * 通过key 从json中 取值
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String getJsonKey(String str, String name) {
		Gson gs = new Gson();
		Map<String, String> map = gs.fromJson(str, Map.class);
		return map.get(name);

	}

	/**
	 * 进行sha1加密
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String str) throws NoSuchAlgorithmException {
		try {

			StringBuffer sb = new StringBuffer();
			// 选择sha1加密
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			// 进行sha1加密
			digest.update(str.getBytes());
			// byte数组
			byte[] digbyte = digest.digest();
			// 遍历byte数组
			for (byte b : digbyte) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 文件上传到服务器 可以自己定义文件格式
	 *
	 */
	public static String ufileToServer(String path, File file, String fileName,
			String fileType, boolean reName) throws IOException {

		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);// 服务器路径
		if (file != null) {
			File savedir = new File(realpath);// 建立目录
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			File saveFile;
			// fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

			// 重新命名 以免重名
			if (reName) {

				saveFile = new File(savedir, java.util.UUID.randomUUID() + "."
						+ fileType);

				FileUtils.copyFile(file, saveFile);
				return path + "/" + saveFile.getName();
			} else
				saveFile = new File(savedir, fileName);

			FileUtils.copyFile(file, saveFile);

			return path + "/" + saveFile.getName();
		}

		return null;

	}

	/**
	 * 删除文件
	 *
	 */
	public static Boolean fileRemove(String path) throws IOException {
		Boolean b = true;
		File file = new File(ServletActionContext.getServletContext()
				.getRealPath(path));
		if (file.exists()) {
			file.delete();
		} else {
			b = false;
		}

		return b;

	}

	/**
	 * 查看文件是否存在 存在 true 否 false
	 *
	 */
	public static Boolean isEmptyfile(String path) throws IOException {
		Boolean b = true;
		if (new File(ServletActionContext.getServletContext().getRealPath(path))
				.exists()) {
			System.out.println("存在");
		} else {
			b = false;
		}

		return b;

	}

	/**
	 * 文件上传到服务器
	 *
	 */
	public static String fileToServer(String path, File file, String fileName,
			String fileType, boolean reName) throws IOException {

		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);// 服务器路径
		if (file != null) {
			File savedir = new File(realpath);// 建立目录
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			File saveFile;
			fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

			// 重新命名 以免重名
			if (reName) {

				saveFile = new File(savedir, java.util.UUID.randomUUID() + "."
						+ fileType);

				FileUtils.copyFile(file, saveFile);
				return path + "/" + saveFile.getName();
			} else
				saveFile = new File(savedir, fileName);

			FileUtils.copyFile(file, saveFile);

			return path + "/" + saveFile.getName();
		}

		return null;

	}

	/**
	 * 获取文件的大小 单位为KB,保留两位小数
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static float fileSize(File file) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		float size = 0;
		size = fis.available() / 1024;
		BigDecimal b = new BigDecimal(size);
		float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return f1;
	}

	/**
	 * 获取文件的大小 单位为MB、 ,保留两位小数
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static float fileSizem(File file) throws IOException {

		FileInputStream fis = new FileInputStream(file);
		float size = 0;
		size = fis.available() / 1024 / 1024;
		BigDecimal b = new BigDecimal(size);
		float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return f1;
	}

	/**
	 * 获得本地时间
	 * 
	 * @return
	 */
	public static String getNowTime() {

		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		return matter1.format(dt);

	}

	/**
	 * 改时间格式
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String setTime(String dt1) throws ParseException {

		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

		// Date或者String转化为时间戳
		Date date = matter1.parse(dt1);

		System.out.print("时间戳:" + date.getTime());

		return matter1.format(date);

	}

	/**
	 * 获得本地时间
	 * 
	 * @return
	 */
	public static String getSFMTime() {
		//
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("HH:mm:ss");
		return matter1.format(dt);

	}

	/**
	 * +1 获得昨天 -1获取明天
	 * 
	 * @return
	 */
	public static String getNumTime(int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day); // 得到前一天
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);

	}

	/**
	 * 上周1 时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek1() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 2); // 获取本周一的日期

		return df.format(cal.getTime());
	}

	/**
	 * 上周日 时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek7() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		cal.set(Calendar.DAY_OF_WEEK, 1); // 获

		return df.format(cal.getTime());
	}

	/**
	 * 封装response
	 * 
	 * @return
	 * @throws IOException
	 */
	public static PrintWriter Out() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		return out;

	}

	/**
	 * 封装Gson to Json
	 * 
	 * @param object
	 * @return
	 */
	public static String ToJson(Object object) {
		Gson gson = new Gson();
		if (object != null) {
			return gson.toJson(object);
		} else {
			return null;
		}

	}

	/**
	 * 获取 session
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Object getsession() {

		return ActionContext.getContext().getSession();

	}

	/**
	 * 获取 request
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HttpServletRequest request()
			throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		return request;

	}

	/**
	 * 获取 response
	 * 
	 * @return
	 */
	public static <HHttpServletResponse> HHttpServletResponse response() {
		HHttpServletResponse response = (HHttpServletResponse) ServletActionContext
				.getResponse();

		return response;

	}

	/**
	 * gyn 电子邮件发送东西 需要Email
	 */
	public static void Email(String Email, String information)
			throws IOException {
		// 发送邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.chaimiyouxi.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("system@chaimiyouxi.com");
		mailInfo.setPassword("nandayong11");// 邮箱密码
		mailInfo.setFromAddress("system@chaimiyouxi.com");
		// 发送的地址
		mailInfo.setToAddress(Email);
		// 标题
		mailInfo.setSubject("家长之友用户体验反馈信息");
		// 时间

		mailInfo.setContent("<h1 style='color: red;'>" + getNowTime() + "<br>"
				+ information + "<h1>");
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);//发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}

	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * 向手机号发送信息
	 */
	public static void setphone(String phone, String textp) {
		try {

			String account = "cf_wcsk_jztd";// 用户名 cf_wcsk_jztd
			String pwd = "wcsk1212";// 密码 wcsk1212
			String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";// 地址
			// 输入要发送的信息
			// String content = new String(textp+"!");
			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);// 允许连接提交信息
			connection.setRequestMethod("POST");// 网页提交方式“GET”、“POST”
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			StringBuffer sb = new StringBuffer();
			sb.append("&account=" + account);
			sb.append("&password=" + pwd);
			sb.append("&content=" + textp);
			sb.append("&mobile=" + phone);// 要发送的手机号
			OutputStream os = connection.getOutputStream();
			os.write(sb.toString().getBytes());
			os.close();

			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			System.out.println(result);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * 
	 * @param 年
	 *            /月/日 2015/08/24命名
	 */
	public static String getNameDayTime() {
		Calendar cal = Calendar.getInstance();
		String name = "";
		name = Integer.valueOf(cal.get(Calendar.YEAR)).toString();
		name += "/";
		if (cal.get(Calendar.MONTH) + 1 < 10) {
			name = name + "0"
					+ Integer.valueOf(cal.get(Calendar.MONTH) + 1).toString();
		} else {
			name = name
					+ Integer.valueOf(cal.get(Calendar.MONTH) + 1).toString();
		}
		name += "/";
		if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
			name = name
					+ "0"
					+ Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH))
							.toString();
		} else {
			name = name
					+ Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH))
							.toString();
		}
		// name += "/";
		// if (cal.get(Calendar.HOUR) + 1 < 10) {
		// name = name + "0"
		// + Integer.valueOf(cal.get(Calendar.HOUR)).toString();
		// } else {
		// name = name
		// + Integer.valueOf(cal.get(Calendar.MONTH) + 1).toString();
		// }
		// name += "/";
		// if (cal.get(Calendar.MINUTE) < 10) {
		// name = name + "0"
		// + Integer.valueOf(cal.get(Calendar.MINUTE)).toString();
		// } else {
		// name = name + Integer.valueOf(cal.get(Calendar.MINUTE)).toString();
		// }
		// if (cal.get(Calendar.SECOND) < 10) {
		// name = name + "0"
		// + Integer.valueOf(cal.get(Calendar.SECOND)).toString();
		// } else {
		// name = name + Integer.valueOf(cal.get(Calendar.SECOND)).toString();
		// }

		return name;
	}

	/**
	 * 计算时间相减
	 * 
	 * @param StartingTime
	 * @param EndTime
	 * @return
	 */
	public static int calculatingTime(String StartingTime, String EndTime) {
		long startT = fromDateStringTLong(EndTime); // 定义结束时间
		long endT = fromDateStringTLong(StartingTime); // 定义开始时间
		long ss = (startT - endT) / (1000); // 共计秒数
		int MM = (int) ss / 60; // 共计分钟数
		int hh = (int) ss / 3600; // 共计小时数
		int dd = (int) hh / 24; // 共计天数
		return hh;

	}

	public static void main(String[] args) throws IOException,
			InterruptedException, ParseException {
		// System.out.println(calculatingTime("2015-11-01 14:49:00",
		// "2015-12-03 14:49:00"));
		// String str = "123456789";
		// String nickname = "123456789";
		// nickname = nickname.substring(0, (nickname.length() > 20 ? 20
		// : nickname.length()));
		// System.out.println(nickname);
		// System.out.println(getNameDayTime());
		// System.out.println(setTime("1015-11-23  16:38:54"));
	}
	public static long fromDateStringTLong(String inVal) { // 此方法计算时间毫秒
		Date date = null; // 定义时间类型
		SimpleDateFormat inputFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			date = inputFormat.parse(inVal); // 将字符型转换成日期型
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime(); // 返回毫秒数
	}


}
