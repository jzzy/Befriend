package com.befriend.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.befriend.util.OpeFunction;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private File[] fileList;
	private String[] fileListFileName;
	private String[] fileListContentType;

	public void upload() throws Exception {
		System.out.println("��һ�ν���upload");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		String path = ServletActionContext.getServletContext().getRealPath("/") + "/file/upload/";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (fileList != null && fileList.length > 0) {
			System.out.println("�ļ��ϴ���");
			for (int i = 0; i < fileList.length; i++) {
				try {
					File saveFile = new File(path + fileListFileName[i]);
					FileUtils.copyFile(fileList[i], saveFile);
					String src = ServletActionContext.getServletContext()
							.getRealPath("//file/upload/" + fileListFileName[i]);
					String srcp = ServletActionContext.getServletContext().getContextPath() + "/file/upload/"
							+ fileListFileName[i];
					File f = new File(src);
					String ffName = f.getName();
					String type = ffName.substring(ffName.lastIndexOf("."), ffName.length());

					BufferedImage sourceImg = ImageIO.read(new FileInputStream(f));

					float fimg = OpeFunction.fileSize(f);
					System.out.println(sourceImg.getWidth() + "*" + sourceImg.getHeight() + "ѹ��ǰͼƬ��СΪ:" + fimg);
					System.out.println("�ļ�����" + type);
					if (OpeFunction.imgNarrow(sourceImg.getWidth(), sourceImg.getHeight(), src, src,type)) {
						srcp = (srcp.split(type)[0] + ".JPEG");
						src = (src.split(type)[0] + ".JPEG");
					}
					f = new File(src);
					fimg = OpeFunction.fileSize(f);
					sourceImg = ImageIO.read(new FileInputStream(f));
					System.out.println(sourceImg.getWidth() + "*" + sourceImg.getHeight() + "ѹ����ͼƬ��СΪ:" + fimg);

					System.out.println("Ŀ¼Ϊ��" + srcp);
					response.getWriter().println(srcp);
					System.out.println("�ļ��ϴ�" + srcp);

				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}

	public File[] getFileList() {
		return fileList;
	}

	public void setFileList(File[] fileList) {
		this.fileList = fileList;
	}

	public String[] getFileListFileName() {
		return fileListFileName;
	}

	public void setFileListFileName(String[] fileListFileName) {
		this.fileListFileName = fileListFileName;
	}

	public String[] getFileListContentType() {
		return fileListContentType;
	}

	public void setFileListContentType(String[] fileListContentType) {
		this.fileListContentType = fileListContentType;
	}

}
