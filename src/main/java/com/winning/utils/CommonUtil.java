package com.winning.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.opensymphony.xwork2.util.ClassLoaderUtil;

/**
 * 图片上传下载
 * @ClassName: CommonUtil
 * @Description: TODO (用一句话描述)
 * @author  Ligh
 * @date 2017年7月5日下午5:08:46
 */
public class CommonUtil {
	private static final String ACCESS_ID = "LTAIDh9kbiyFNRQg";
	private static final String ACCESS_KEY = "5BwQDwnoONe0bOxZrCElOypxhIsGR4";
	private static final String OSS_ENDPOINT = "https://oss.aliyuncs.com/";
	private static final String BUCKETNAME = "guizhou12320";
	private static final String KEY = "guizhou12320/";
	public static final String OSSPATH = "https://guizhou12320.oss-cn-hangzhou.aliyuncs.com/guizhou12320/";
	


	public static boolean fileUp(String path, InputStream stream) {
		try {
			OutputStream bos = new FileOutputStream(path);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取图片名称 规则： 以当前时间 + 一个随机数
	 * 
	 * @param modNam
	 *            图片命名均以这个参数开始
	 * @param postfix
	 *            图片格式
	 * @return 图片名称
	 */
	public static String getPicName(String postfix) {
		StringBuffer sb = new StringBuffer();
		sb.append(MyTimeUtil.DateToStr(MyTimeUtil.yyyy_MM_dd, new Date()) + "-" + UUIDGenerator.getFullUUID()).append(".").append(postfix);// 重新构建文件名
		return sb.toString();
	}

	/**
	 * 检查文件夹是否存在，不存在则创建一个
	 * 
	 * @param path
	 */
	public static void checkDir(String path) {
		File filePath = new File(path);
		if (!filePath.exists()) {
			try {
				filePath.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取当前项目webinfo的上级目录，用于创建存放图片的临时文件夹
	 * 
	 * @return 绝对路径
	 */
	public static String getWeb_InfPath(String classPath) {

		classPath = classPath.replace("/", File.separator).replace("\\", File.separator);
		classPath = classPath.substring(classPath.indexOf(File.separator) + 1);
		classPath = classPath.substring(0, classPath.length() - 1);

		String className = ClassLoaderUtil.class.toString();

		while (className.indexOf(".") != -1) {
			classPath = classPath.substring(0, classPath.lastIndexOf(File.separator));
			className = className.substring(className.indexOf(".") + 1);
		}

		return classPath;
	}

	/**
	 * 下载文件
	 * 
	 * @return success(true) or failed(false)
	 */
	public void downFile(String path, HttpServletResponse response) {
		try {
			File file = new File(path);
			response.setContentType("image/jpeg");
			response.setCharacterEncoding("UTF-8");
			response.setContentLength((int) file.length());
			FileInputStream fis = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			int bytesRead = 0;
			byte[] buffer = new byte[(int) file.length()];
			while ((bytesRead = fis.read(buffer, 0, buffer.length)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @return success(true) or failed(false)
	 */
	public void downFileOss(String path, HttpServletResponse response) {
		try {
			String file = KEY + path;
			OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
			OSSObject ossObject = client.getObject(new GetObjectRequest(BUCKETNAME, file));
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte['?'];
			int bytesRead;
			while ((bytesRead = ossObject.getObjectContent().read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean fileUpOss(String newName, File file) {
		try {
			OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
			uploadFile(client, BUCKETNAME, KEY + newName, file);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 上传文件
	private static void uploadFile(OSSClient client, String bucketName, String key, File file) throws OSSException, ClientException, FileNotFoundException {
		// File file = new File(filename);

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.length());
		// 可以在metadata中标记文件类型
		objectMeta.setContentType("image/jpeg");

		InputStream input = new FileInputStream(file);
		client.putObject(bucketName, key, input, objectMeta);
	}

	/**
	 * MultipartFile 转换成File
	 * 
	 * @param multfile
	 *            原文件类型
	 * @return File
	 * @throws IOException
	 */
	public static File multipartToFile(MultipartFile multfile) throws IOException {
		CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
		// 这个myfile是MultipartFile的
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File file = fi.getStoreLocation();
		// 手动创建临时文件
		if (file.length() < 2048) {
			File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getName());
			multfile.transferTo(tmpFile);
			return tmpFile;
		}
		return file;
	}

	/**
	 * 为文件重新命名，命名规则为当前系统时间毫秒数
	 * 
	 * @return string
	 */
	private String getFileNameNew() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}
}
