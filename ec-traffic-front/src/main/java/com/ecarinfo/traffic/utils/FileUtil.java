package com.ecarinfo.traffic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.traffic.dto.FileDto;

/**
 * @Description: 文件操作工具类
 * @Date 2012-11-6
 * @Version V1.0
 */

public class FileUtil {
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	private static final int BUFFER = 1024;

	/**
	 * @Title:功 能: 拷贝文件(只能拷贝文件)
	 * @param: strSourceFileName 指定的文件全路径名
	 * @param: strDestDir 拷贝到指定的文件夹
	 * @return 如果成功true;否则false
	 */
	public boolean copyTo(String strSourceFileName, String strDestDir) {
		File fileSource = new File(strSourceFileName);
		File fileDest = new File(strDestDir);

		// 如果源文件不存或源文件是文件夹
		if (!fileSource.exists() || !fileSource.isFile()) {
			logger.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
			return false;
		}

		// 如果目标文件夹不存在
		if (!fileDest.isDirectory() || !fileDest.exists()) {
			if (!fileDest.mkdirs()) {
				logger.debug("目录文件夹不存，在创建目标文件夹时失败!");
				return false;
			}
		}
		try {
			String strAbsFilename = strDestDir + File.separator
					+ fileSource.getName();

			FileInputStream fileInput = new FileInputStream(strSourceFileName);
			FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

			logger.debug("开始拷贝文件:");

			int count = -1;

			long nWriteSize = 0;
			long nFileSize = fileSource.length();

			byte[] data = new byte[BUFFER];

			while (-1 != (count = fileInput.read(data, 0, BUFFER))) {
				fileOutput.write(data, 0, count);
				nWriteSize += count;

				long size = (nWriteSize * 100) / nFileSize;
				long t = nWriteSize;
				String msg = null;
				if (size <= 100 && size >= 0) {
					msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
					logger.debug(msg);
				} else if (size > 100) {
					msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
					logger.debug(msg);
				}
			}

			fileInput.close();
			fileOutput.close();

			logger.debug("拷贝文件成功!");
			return true;

		} catch (Exception e) {
			logger.debug("异常信息：[");
			logger.error(e);
			logger.debug("]");
			return false;
		}
	}

	/**
	 * @Title:删除指定的文件
	 * @param: strFileName 指定绝对路径的文件名
	 * @return: 如果删除成功true否则false
	 */
	public boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			logger.debug("错误: " + strFileName + "不存在!");
			return false;
		}

		return fileDelete.delete();
	}

	/**
	 * @Title:移动文件(只能移动文件)
	 * @param: strSourceFileName 是指定的文件全路径名
	 * @param: strDestDir 移动到指定的文件夹中
	 * @return 如果成功true; 否则false
	 */
	public boolean moveFile(String strSourceFileName, String strDestDir) {
		if (copyTo(strSourceFileName, strDestDir))
			return this.delete(strSourceFileName);
		else
			return false;
	}

	/**
	 * @Title:创建文件夹
	 * @param: strDir 要创建的文件夹名称
	 * @return 如果成功true;否则false
	 */
	public static boolean makedir(String strDir) {
		File fileNew = new File(strDir);
		if (!fileNew.exists()) {
			return fileNew.mkdirs();
		} else {
			return true;
		}
	}

	/**
	 * @Title:删除文件夹
	 * @param: strDir 要删除的文件夹名称
	 * @return 如果成功true;否则false
	 */
	public boolean rmdir(String strDir) {
		File rmDir = new File(strDir);
		if (rmDir.isDirectory() && rmDir.exists()) {
			String[] fileList = rmDir.list();
			for (int i = 0; i < fileList.length; i++) {
				String subFile = strDir + File.separator + fileList[i];
				File tmp = new File(subFile);
				if (tmp.isFile())
					tmp.delete();
				else if (tmp.isDirectory())
					rmdir(subFile);
				else {
					logger.debug("error!");
				}
			}
			rmDir.delete();
		} else
			return false;
		return true;
	}

	/**
	 * @Title:写文件到本地
	 * @param in
	 * @param: filePath 上传文件的路径
	 * @throws IOException
	 */
	public static void uploadFile(InputStream in, String filePath)
			throws IOException {
		// 如果不存在，就创建文件夹
		if (makedir(filePath)) {
			FileOutputStream fs = new FileOutputStream(filePath);
			byte[] buffer = new byte[1024 * 1024];
			@SuppressWarnings("unused")
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = in.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			in.close();
		}
	}

	// 上传文件到服务器
	public static FileDto uploadFileService(HttpServletRequest request,
			String urlName) {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		String dayFmt = "yyyy/MM/dd";
		String fileDir = FileStorageUtil.getAbsoluteStorage(request, urlName,
				dayFmt) + "/";
		String fileUploadPath = FileStorageUtil.getWebStorage(urlName, dayFmt)
				+ "/";
		FileDto fileDto = new FileDto();
		fileDto.setBasePath(fileDir);

		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartRequest.getFile((String) iter
						.next());
				if (file != null) {
					// 重点就是这两句
					if (FileUtil.makedir(fileDir)) {
						String fileName = file.getOriginalFilename();
						String endName = DateUtils.dateToString(new Date(), "yyyyMMddhh24mmss");
						String beginName = fileName.indexOf(".") != -1 ? fileName.split("\\.")[0]:"";
						String suffix = fileName.indexOf(".") != -1 ? fileName
								.substring(fileName.lastIndexOf("."),
										fileName.length()) : null;
//						String uuid32 = IdentitiesUtil.uuid32();
						String newFileName = beginName+endName
								+ (suffix != null ? suffix : "");//模板名称+时间戳.sxl
						fileDir = fileDir + newFileName;

						fileDto.setFilePath(fileDir);
						fileDto.setFileName(beginName+endName);
						fileDto.setFileSuffix(suffix);
						fileDto.setFileUploadPath(fileUploadPath + newFileName);

						File localFile = new File(fileDir);
						try {
							file.transferTo(localFile);
						} catch (Exception e) {
							logger.debug("文件上传至服务器失败!");
						}
					}
				}
			}
		}
		return fileDto;
	}

	// 文件转换成单位
	public static String getFileSizeStr(Long size) {
		if (size != null) {
			float fileSize = size / 1024;
			String suffix = "KB";
			if (fileSize > 1024) {
				fileSize = fileSize / 1024;
				suffix = "MB";
			}
			if (fileSize > 1024) {
				fileSize = fileSize / 1024;
				suffix = "G";
			}
			String fs = String.valueOf(fileSize);
			int index = fs.indexOf(".");
			if (index > 0) {
				String fsize = fs.substring(0, index);
				Integer fb = Integer
						.valueOf(fs.substring(index + 1, index + 2));
				if (fb > 0) {
					fsize += "." + fb;
				}
				fsize += suffix;
				return fsize;
			}
		}
		return "0KB";
	}
	
}
