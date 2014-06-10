package com.ecarinfo.traffic.dto;

import java.io.Serializable;

public class FileDto implements Serializable {

	private static final long serialVersionUID = 1080394894122956546L;

	private String filePath; // 文件名及路径
	private String fileName; // 文件名称，不带后缀
	private String basePath;// 文件路径

	private String fileSuffix; // 文件后缀

	private String fileUploadPath; // 服务器中上传的路径

	private long successNums;//成功上传的数量
	private long totalNums;//总数
	private long repeatNums;//重复的数量
	private long exitsNums;//已存在的数据量
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public long getSuccessNums() {
		return successNums;
	}

	public void setSuccessNums(long successNums) {
		this.successNums = successNums;
	}

	public long getTotalNums() {
		return totalNums;
	}

	public void setTotalNums(long totalNums) {
		this.totalNums = totalNums;
	}

	public long getRepeatNums() {
		return repeatNums;
	}

	public void setRepeatNums(long repeatNums) {
		this.repeatNums = repeatNums;
	}

	public long getExitsNums() {
		return exitsNums;
	}

	public void setExitsNums(long exitsNums) {
		this.exitsNums = exitsNums;
	}
	
}
