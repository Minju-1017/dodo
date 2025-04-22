package com.dodo.module.file;

import java.io.IOException;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dodo.Constants;

@Service
public class FileService {
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Autowired
	private FileDao dao;
	
	public void uploadFilesToS3(FileDto dto, String fDbTableName, String rSeq, boolean insert) throws Exception {
		MultipartFile[] fUploadFiles = dto.getfUploadFiles();
		if (fUploadFiles == null || fUploadFiles.length == 0) return;
		
		MultipartFile fUploadFile = fUploadFiles[0];
		if (fUploadFile == null || fUploadFile.isEmpty()) return;
		
		String originalfileName = fUploadFile.getOriginalFilename(); 					// 실제 파일명
		String fExt = originalfileName.substring(originalfileName.lastIndexOf(".") + 1); // 확장자
		String fPath = fDbTableName + "/"; 		// Aws 경로(폴더명)
		String fFileName = rSeq + "." + fExt;	// 파일명(연관 관계에 있는 테이블의 seq - 고유값)

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fUploadFile.getSize());
        metadata.setContentType(fUploadFile.getContentType());
        
        // AWS 파일 업로드
        new Thread() {
        	public void run() {
        		try {
        			// https://dodomimi-bucket.s3.ap-northeast-2.amazonaws.com/gameSmallTnFile/파일명.확장자 // 파일명은 rSeq
					amazonS3Client.putObject(Constants.AWS_BUCKET, fPath + fFileName, fUploadFile.getInputStream(), metadata);
				} catch (AmazonServiceException e) {
					e.printStackTrace();
				} catch (SdkClientException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }.start();
    
        String objectUrl = amazonS3Client.getUrl(Constants.AWS_BUCKET, fPath + fFileName).toString(); // AWS 파일 경로
        
        dto.setfDbTableName(fDbTableName);
		dto.setfPath(objectUrl);
		dto.setfFileName(fFileName);
		dto.setfUuidName(UUID.randomUUID().toString() + "." + fExt);
		dto.setfExt(fExt);
		dto.setfSize(fUploadFile.getSize());
		dto.setrSeq(rSeq);
		
		if (insert) {
			dao.insertFile(dto);
		} else {
			dao.updateFile(dto);
		}
	}
	
}
