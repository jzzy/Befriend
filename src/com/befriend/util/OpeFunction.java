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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.befriend.email.MailSenderInfo;
import com.befriend.email.SimpleMailSender;
import com.befriend.entity.User;
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
public class OpeFunction {
	/**
	 * js返回到html
	 * 
	 * @throws IOException
	 */
	public static void outjS(String URL, String string) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String loginPage = URL;
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\">");
		builder.append("alert('" + string + "');");
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
		b = file.delete();

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
		mailInfo.setSubject("家长天地用户体验反馈信息");
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
	 * public static final byte[] Key = "abcdefgh".getBytes(); private static
	 * final String Algorithm = "DES"; //定义 加密算法,可用 DES,DESede,Blowfish
	 * 
	 * // 加密字符串 public static byte[] encryptMode(byte[] keybyte, byte[] src) {
	 * try { // 生成密钥 SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
	 * // 加密 Cipher c1 = Cipher.getInstance(Algorithm);
	 * c1.init(Cipher.ENCRYPT_MODE, deskey); return c1.doFinal(src); } catch
	 * (java.security.NoSuchAlgorithmException e1) { e1.printStackTrace(); }
	 * catch (javax.crypto.NoSuchPaddingException e2) { e2.printStackTrace(); }
	 * catch (java.lang.Exception e3) { e3.printStackTrace(); } return null; }
	 * 
	 * // 解密字符串 public static byte[] decryptMode(byte[] keybyte, byte[] src) {
	 * try { // 生成密钥 SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
	 * // 解密 Cipher c1 = Cipher.getInstance(Algorithm);
	 * c1.init(Cipher.DECRYPT_MODE, deskey); return c1.doFinal(src); } catch
	 * (java.security.NoSuchAlgorithmException e1) { e1.printStackTrace(); }
	 * catch (javax.crypto.NoSuchPaddingException e2) { e2.printStackTrace(); }
	 * catch (java.lang.Exception e3) { e3.printStackTrace(); } return null; }
	 * 
	 * public static void main(String[] args) { // 添加新安全算法,如果用JCE就要把它添加进去
	 * Security.addProvider(new com.sun.crypto.provider.SunJCE()); final byte[]
	 * keyBytes = Key; //8字节的密钥 String szSrc = "这是一个测试abcd";
	 * System.out.println("加密前的字符串:" + szSrc); byte[] encoded =
	 * encryptMode(keyBytes, szSrc.getBytes()); System.out.println("加密后的字符串:" +
	 * new String(encoded)); byte[] srcBytes = decryptMode(keyBytes, encoded);
	 * System.out.println("解密后的字符串:" + (new String(srcBytes))); }
	 * 
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		String fileName = "E:\\xiaoxue.xlsx";

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				// 第一次不读 是标题
				if (rowNum < 1) {
					// System.out.println("列："+xssfRow.getCell(0));
					System.out.println("学校名称：" + xssfRow.getCell(1));
					System.out.println("学校地址：" + xssfRow.getCell(2));
					System.out.println("学区范围：" + xssfRow.getCell(3));
					System.out.println("学区范围内主要街道、小区、楼盘、单位宿舍："
							+ xssfRow.getCell(4));
					System.out.println("第" + numSheet + "个工作表Sheet,的第" + rowNum
							+ "行");
				}

			}
		}
	}

	private static String getValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {

			return String.valueOf(xssfCell.getNumericCellValue());
		} else {

			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

}
