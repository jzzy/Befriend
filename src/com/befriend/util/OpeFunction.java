package com.befriend.util;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.befriend.dao.ApputilDAO;
import com.befriend.email.MailSenderInfo;
import com.befriend.email.SimpleMailSender;
import com.befriend.entity.SortModel;
import com.befriend.entity.User;
import com.befriend.wechat.WechatKit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
 * ������
 * 
 * @author gyn
 *
 */

@SuppressWarnings("all")
public class OpeFunction {

	/**
	 * ��ȡ�ļ� תjson
	 * 
	 * @throws IOException
	 */
	public static List outFile() throws IOException {
		File file = new File(
				"C:\\Users\\���\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Befriend\\address.json");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		Gson gs = new Gson();
		List<SortModel> lsm = gs.fromJson(br.readLine(), new TypeToken<List<SortModel>>() {
		}.getType());

		for (SortModel sortModel : lsm) {
			System.out.println(sortModel.getName() + sortModel.getSortLetters());

		}
		return lsm;
	}

	/**
	 * js���ص�html
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
	 * �� ���� true
	 * 
	 * ���� ���� false
	 * 
	 * @param rstart
	 * @param rend
	 * @return
	 */
	public static boolean isEmpty(String string) {

		if (string != null) {
			if (StringUtils.isEmpty(string.trim())) {
				System.out.println("��");
				return true;
			} else {
				System.out.println("����");
				return false;
			}
		} else {
			System.out.println("��");
			return true;
		}
	}

	// private static final int FIRST_DAY_OF_WEEK = 0;
	/**
	 * ��ȡ����ip
	 * 
	 * @throws UnknownHostException
	 */
	public static String getfileName() {
		Calendar cal = Calendar.getInstance();// ʹ��������
		System.out.println("��" + cal.get(Calendar.YEAR));
		System.out.println("��" + cal.get(Calendar.MONTH) + 1);
		System.out.println("��" + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("ʱ" + cal.get(Calendar.HOUR));
		System.out.println("��" + cal.get(Calendar.MINUTE));
		System.out.println("��" + cal.get(Calendar.SECOND));
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
	 * ��ȡ����ip
	 * 
	 * @throws UnknownHostException
	 */
	public static String getLIP() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		return addr.getHostAddress().toString();
	}

	/**
	 * ͨ��key ��json�� ȡֵ
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static User getJsonKeyUs(String str) {
		Gson gs = new Gson();
		User u = gs.fromJson(str, User.class);
		return u;

	}

	/**
	 * ͨ��key ��json�� ȡֵ
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String getJsonKey(String str, String name) {
		Gson gs = new Gson();
		Map<String, String> map = gs.fromJson(str, Map.class);
		return map.get(name);

	}

	/**
	 * ����sha1����
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String str) throws NoSuchAlgorithmException {
		try {

			StringBuffer sb = new StringBuffer();
			// ѡ��sha1����
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			// ����sha1����
			digest.update(str.getBytes());
			// byte����
			byte[] digbyte = digest.digest();
			// ����byte����
			for (byte b : digbyte) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * �ļ��ϴ��������� �����Լ������ļ���ʽ
	 *
	 */
	public static String ufileToServer(String path, File file, String fileName, String fileType, boolean reName)
			throws IOException {

		String realpath = ServletActionContext.getServletContext().getRealPath(path);// ������·��
		if (file != null) {
			File savedir = new File(realpath);// ����Ŀ¼
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			File saveFile;
			// fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

			// �������� ��������
			if (reName) {

				saveFile = new File(savedir, java.util.UUID.randomUUID() + "." + fileType);

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
	 * ɾ���ļ�
	 *
	 */
	public static Boolean fileRemove(String path) throws IOException {
		Boolean b = true;
		File file = new File(ServletActionContext.getServletContext().getRealPath(path));
		if (file.exists()) {
			file.delete();
		} else {
			b = false;
		}

		return b;

	}

	/**
	 * �鿴�ļ��Ƿ���� ���� true �� false
	 *
	 */
	public static Boolean isEmptyfile(String path) throws IOException {
		Boolean b = true;
		if (new File(ServletActionContext.getServletContext().getRealPath(path)).exists()) {
			System.out.println("����");
		} else {
			b = false;
		}

		return b;

	}

	/**
	 * �ļ��ϴ���������
	 *
	 */
	public static String fileToServer(String path, File file, String fileName, String fileType, boolean reName)
			throws IOException {

		String realpath = ServletActionContext.getServletContext().getRealPath(path);// ������·��
		if (file != null) {
			File savedir = new File(realpath);// ����Ŀ¼
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();
			File saveFile;
			fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

			// �������� ��������
			if (reName) {

				saveFile = new File(savedir, java.util.UUID.randomUUID() + "." + fileType);

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
	 * ��ȡ�ļ��Ĵ�С ��λΪKB,������λС��
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
	 * ��ȡ�ļ��Ĵ�С ��λΪMB�� ,������λС��
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
	 * ��ñ���ʱ��
	 * 
	 * @return
	 */
	public static String getNowTime() {

		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		return matter1.format(dt);

	}

	/**
	 * ��ʱ���ʽ
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String setTime(String dt1) throws ParseException {

		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

		// Date����Stringת��Ϊʱ���
		Date date = matter1.parse(dt1);

		System.out.print("ʱ���:" + date.getTime());

		return matter1.format(date);

	}

	/**
	 * ��ñ���ʱ��
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
	 * +1 ������� -1��ȡ����
	 * 
	 * @return
	 */
	public static String getNumTime(int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day); // �õ�ǰһ��
		Date date = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);

	}

	/**
	 * ����1 ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek1() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, 2); // ��ȡ����һ������

		return df.format(cal.getTime());
	}

	/**
	 * ������ ʱ��
	 * 
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek7() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		cal.set(Calendar.DAY_OF_WEEK, 1); // ��

		return df.format(cal.getTime());
	}

	/**
	 * ��װresponse
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
	 * ��װGson to Json
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
	 * ��ȡ session
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Object getsession() {

		return ActionContext.getContext().getSession();

	}

	/**
	 * ��ȡ request
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HttpServletRequest request() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		return request;

	}

	/**
	 * ��ȡ response
	 * 
	 * @return
	 */
	public static <HHttpServletResponse> HHttpServletResponse response() {
		HHttpServletResponse response = (HHttpServletResponse) ServletActionContext.getResponse();

		return response;

	}

	/**
	 * gyn �����ʼ����Ͷ��� ��ҪEmail
	 */
	public static void Email(String Email, String information) throws IOException {
		// �����ʼ�
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.chaimiyouxi.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("system@chaimiyouxi.com");
		mailInfo.setPassword("nandayong11");// ��������
		mailInfo.setFromAddress("system@chaimiyouxi.com");
		// ���͵ĵ�ַ
		mailInfo.setToAddress(Email);
		// ����
		mailInfo.setSubject("�ҳ�֮���û����鷴����Ϣ");
		// ʱ��

		mailInfo.setContent("<h1 style='color: red;'>" + getNowTime() + "<br>" + information + "<h1>");
		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);//���������ʽ
		sms.sendHtmlMail(mailInfo);// ����html��ʽ
	}

	/**
	 * ��ȡ�ͻ���ip
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
	 * ���ֻ��ŷ�����Ϣ
	 */
	public static void setphone(String phone, String textp) {
		try {

			String account = "cf_wcsk_jztd";// �û��� cf_wcsk_jztd
			String pwd = "wcsk1212";// ���� wcsk1212
			String postUrl = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";// ��ַ
			// ����Ҫ���͵���Ϣ
			// String content = new String(textp+"!");
			URL url = new URL(postUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);// ���������ύ��Ϣ
			connection.setRequestMethod("POST");// ��ҳ�ύ��ʽ��GET������POST��
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Connection", "Keep-Alive");
			StringBuffer sb = new StringBuffer();
			sb.append("&account=" + account);
			sb.append("&password=" + pwd);
			sb.append("&content=" + textp);
			sb.append("&mobile=" + phone);// Ҫ���͵��ֻ���
			OutputStream os = connection.getOutputStream();
			os.write(sb.toString().getBytes());
			os.close();

			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
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
	 * @param ��
	 *            /��/�� 2015/08/24����
	 */
	public static String getNameDayTime() {
		Calendar cal = Calendar.getInstance();
		String name = "";
		name = Integer.valueOf(cal.get(Calendar.YEAR)).toString();
		name += "/";
		if (cal.get(Calendar.MONTH) + 1 < 10) {
			name = name + "0" + Integer.valueOf(cal.get(Calendar.MONTH) + 1).toString();
		} else {
			name = name + Integer.valueOf(cal.get(Calendar.MONTH) + 1).toString();
		}
		name += "/";
		if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
			name = name + "0" + Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH)).toString();
		} else {
			name = name + Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH)).toString();
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
	 * ����ʱ�����
	 * 
	 * @param StartingTime
	 * @param EndTime
	 * @return
	 */
	public static int calculatingTime(String StartingTime, String EndTime) {
		long startT = fromDateStringTLong(EndTime); // �������ʱ��
		long endT = fromDateStringTLong(StartingTime); // ���忪ʼʱ��
		long ss = (startT - endT) / (1000); // ��������
		int MM = (int) ss / 60; // ���Ʒ�����
		int hh = (int) ss / 3600; // ����Сʱ��
		int dd = (int) hh / 24; // ��������
		return hh;

	}

	/**
	 * ����Ϊָ����С
	 * 
	 * @param w
	 * @param h
	 * @param srcImageFile
	 * @param newName
	 * @throws Exception
	 */
	public static boolean imgNarrow(int w, int h, String srcImageFile, String newName,String type) throws Exception {
		try {

			newName = newName.split(type)[0] + ".JPEG";
			System.out.println(srcImageFile);
			System.out.println(newName);
			// String srcImageFile = "C:/Users/gyn/Pictures/Camera
			// Roll/05094b36acaf2edde146bfce8d1001e939019366.jpg";
			// �õ�ԴͼƬ
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			// ������ͼ������Ű汾
			java.awt.Image prevImage = bi.getScaledInstance(w, h, Image.SCALE_DEFAULT);
			// �����image
			BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			// ��ͼ�������ȥ
			Graphics graphics = tag.createGraphics();
			graphics.drawImage(prevImage, 0, 0, w, h, null);
			graphics.dispose();
			// �����
			// String newName = "C:/Users/gyn/Pictures/Camera Roll/66.jpg";

			OutputStream outs = new FileOutputStream(newName);
			// ����
			ImageIO.write(tag, "JPEG", outs);
			outs.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * �ü�
	 * 
	 * @throws Exception
	 */
	public static void testOne() throws Exception {
		try {

			int x = 100;// Ҫ��ȡ�ľ��ζ����� x λ��

			int y = 100;// Ҫ��ȡ�ľ��ζ����� y λ��

			int w = 100;// Ҫ��ȡ�ľ��ο��

			int h = 100;// Ҫ��ȡ�ľ��θ߶�

			//
			String srcImageFile = "C:/Users/gyn/Pictures/Camera Roll/05094b36acaf2edde146bfce8d1001e939019366.jpg";
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			// ����Ҫ�ü�������ͼƬ
			CropImageFilter cropFilter = new CropImageFilter(x, y, w, h);
			java.awt.Image tagImg = Toolkit.getDefaultToolkit()
					.createImage(new FilteredImageSource(bi.getSource(), cropFilter));
			//
			String newName = "C:/Users/gyn/Pictures/Camera Roll/99.jpg";
			OutputStream outs = new FileOutputStream(newName);
			//
			BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = tag.createGraphics();
			graphics.drawImage(tagImg, 0, 0, w, h, null);
			graphics.dispose();
			ImageIO.write(tag, "JPEG", outs);
			outs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) throws Exception {
		
		
		
		
	
			
		Hashtable hash=new Hashtable(2,(float)0.8);
	//������һ����ϣ��Ķ���hash����ʼ����Ϊ2��װ������Ϊ0.8

	hash.put("Jiangsu","Nanjing");
	//���ַ�������Jiangsu������һ�ؼ��֡�Nanjing��,����������hash
	hash.put("Beijing","Beijing");
	hash.put("Zhejiang","Hangzhou");

	System.out.println("The hashtable hash1 is: "+hash);
	System.out.println("The size of this hash table is "+hash.size());
	//��ӡhash�����ݺʹ�С

	Enumeration enum1=hash.elements();
	System.out.print("The element of hash is: ");
	while(enum1.hasMoreElements())
	System.out.print(enum1.nextElement()+" ");
	System.out.println();
	//���δ�ӡhash�е�����
	if(hash.containsKey("Jiangsu"))
	System.out.println("The capatial of Jiangsu is "+hash.get("Jiangsu"));
	hash.remove("Beijing");
	//ɾ���ؼ���Beijing��Ӧ����
	System.out.println("The hashtable hash2 is: "+hash);
	System.out.println("The size of this hash table is "+hash.size());
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// testOne();
		// imgNarrow();
		// File file=new
		// File("C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Befriend\\address.json");
		// FileReader fr = new FileReader(file);
		// BufferedReader br = new BufferedReader(fr);
		// Gson gs = new Gson();
		// List<SortModel> lsm = gs.fromJson(br.readLine(), new
		// TypeToken<List<SortModel>>(){}.getType());
		//
		// for (SortModel sortModel : lsm) {
		// System.out.println(sortModel.getName()+sortModel.getSortLetters());
		// }

		// System.out.println(calculatingTime("2015-11-01 14:49:00",
		// "2015-12-03 14:49:00"));
		// String str = "123456789";
		// String nickname = "123456789";
		// nickname = nickname.substring(0, (nickname.length()-1));
		// System.out.println(nickname);
		// System.out.println(getNameDayTime());
		// System.out.println(setTime("1015-11-23 16:38:54"));

		// System.out.println(calculatingTime("2015-11-01 14:49:00",
		// "2015-12-03 14:49:00"));
		// String str = "123456789";
		// String nickname = "123456789";
		// nickname = nickname.substring(0, (nickname.length() > 20 ? 20
		// : nickname.length()));
		// System.out.println(nickname);
		// System.out.println(getNameDayTime());
		// System.out.println(setTime("1015-11-23 16:38:54"));

		// String
		// str=WechatKit.sendGet("http://restapi.amap.com/v3/place/around?key=5deb2485b0b9546999783b2fab8ff625"
		// + "&location=116.481488,39.990464"
		// + "&keywords=��ʳ"
		// + "&types="
		// + "&offset=10"
		// + "&page=1"
		// + "&extensions=all");
		// JSONObject jsonObject = new JSONObject(str);
		//
		// JSONArray jsonArray =jsonObject.getJSONArray("pois");
		//
		// for (int i = 0; i < jsonArray.length(); i++) {
		// JSONObject jo = jsonArray.getJSONObject(i);
		// System.out.println(jo.getString("name"));
		// System.out.println(jo.getString("type"));
		// System.out.println(jo.getString("location"));
		// System.out.println(jo.getString("tel"));
		//
		//
		// }
		// System.out.println("�����������:"+Distance(42.0090289997803,
		// 121.678251000076,42.0090289997803, 121.678251000076));
	}

	/**
	 * �����������������(��γ��)����
	 * 
	 * @param long1
	 *            ��һ�㾭��
	 * @param lat1
	 *            ��һ��γ��
	 * @param long2
	 *            �ڶ��㾭��
	 * @param lat2
	 *            �ڶ���γ��
	 * @return ���ؾ��� ��λ����
	 */
	public static int Distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // ����뾶
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return (int) d;
	}

	public static long fromDateStringTLong(String inVal) { // �˷�������ʱ�����
		Date date = null; // ����ʱ������
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = inputFormat.parse(inVal); // ���ַ���ת����������
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.getTime(); // ���غ�����
	}

}
