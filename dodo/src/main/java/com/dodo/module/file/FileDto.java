package com.dodo.module.file;

import org.springframework.web.multipart.MultipartFile;

public class FileDto {
	
	private String fDbTableName;
	
	private String fSeq;
	private String fPath;
	private String fFileName;
	private String fUuidName;
	private String fExt;
	private long fSize;
	private String rSeq;
	
	private MultipartFile[] fUploadFiles;
	
	public String getfDbTableName() {
		return fDbTableName;
	}

	public void setfDbTableName(String fDbTableName) {
		this.fDbTableName = fDbTableName;
	}

	public String getfSeq() {
		return fSeq;
	}

	public void setfSeq(String fSeq) {
		this.fSeq = fSeq;
	}

	public String getfPath() {
		return fPath;
	}

	public void setfPath(String fPath) {
		this.fPath = fPath;
	}

	public String getfFileName() {
		return fFileName;
	}

	public void setfFileName(String fFileName) {
		this.fFileName = fFileName;
	}

	public String getfUuidName() {
		return fUuidName;
	}

	public void setfUuidName(String fUuidName) {
		this.fUuidName = fUuidName;
	}

	public String getfExt() {
		return fExt;
	}

	public void setfExt(String fExt) {
		this.fExt = fExt;
	}

	public long getfSize() {
		return fSize;
	}

	public void setfSize(long fSize) {
		this.fSize = fSize;
	}

	public String getrSeq() {
		return rSeq;
	}

	public void setrSeq(String rSeq) {
		this.rSeq = rSeq;
	}

	public MultipartFile[] getfUploadFiles() {
		return fUploadFiles;
	}

	public void setfUploadFiles(MultipartFile[] fUploadFiles) {
		this.fUploadFiles = fUploadFiles;
	}

}
