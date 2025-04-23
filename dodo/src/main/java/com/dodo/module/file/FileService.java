package com.dodo.module.file;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dodo.Constants;
import com.dodo.module.game.GameDto;

@Service
public class FileService {
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Autowired
	private FileDao dao;
	
	public FileDto selectOne(FileDto fileDto, String fDbTableName) {
		fileDto.setfDbTableName(fDbTableName);
		return dao.selectOne(fileDto);
	}
	
	/**
	 * DB Table 1개마다 rSeq당 1개의 파일만 업로드
	 * @param fileDto
	 * @param fDbTableNames
	 * @param rSeq
	 * @param insert
	 * @throws Exception
	 */
	public void uploadFilesToS3(FileDto fileDto, String[] fDbTableNames, String rSeq) throws Exception {
		
		// fileDto.getfUploadFiles()의 length는 fDbTableNames length와 일치함
		MultipartFile[] fUploadFiles = fileDto.getfUploadFiles(); 
		
		if (fUploadFiles == null || fUploadFiles.length == 0 || fUploadFiles.length != fDbTableNames.length) return;
		
		for (int i = 0; i < fUploadFiles.length; i++) {
			MultipartFile fUploadFile = fUploadFiles[i];
			String fDbTableName = fDbTableNames[i];
			
			if (!(fUploadFile == null || fUploadFile.isEmpty() || 
					fDbTableName == null || fDbTableName.equals(""))) {
				// AWS에 올릴 파일명 만들기
				String originalfileName = fUploadFile.getOriginalFilename();						// 실제 파일명
				String fExt = originalfileName.substring(originalfileName.lastIndexOf(".") + 1); 	// 확장자
				String fPath = fDbTableName + "/"; 		// Aws 경로(폴더명)
				String fFileName = rSeq + "." + fExt;	// 파일명(연관 관계에 있는 테이블의 seq - 고유값)
				
				/* 파일명 매번 다르게 할 경우 년월일시 이용 예시
					String nowString = UtilDateTime.nowString();
					String pathDate = nowString.substring(0, 4) + "/" + nowString.substring(5, 7) + "/" + nowString.substring(8, 10); 
					String path = pathModule + "/" + type + "/" + pathDate + "/";
				*/
				
				// 메타 데이터
		        ObjectMetadata metadata = new ObjectMetadata();
		        metadata.setContentLength(fUploadFile.getSize());
		        metadata.setContentType(fUploadFile.getContentType());
		        
		        // AWS 파일 업로드 - 오래 걸릴 수 있으므로 Thread 이용
		        new Thread() {
		        	public void run() {
		        		try {
		        			// https://dodomimi-bucket.s3.ap-northeast-2.amazonaws.com/DB Table명/파일명.확장자 => 파일명은 고유값인 rSeq
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
		    
		        String objectUrl = 
		        		amazonS3Client.getUrl(Constants.AWS_BUCKET, fPath + fFileName).toString(); // AWS 파일 Full Path
		        
		        // DB insert/update할 데이터 저장
		        fileDto.setfDbTableName(fDbTableName);
				fileDto.setfPath(objectUrl);
				fileDto.setfFileName(fFileName);
				fileDto.setfUuidName(UUID.randomUUID().toString() + "." + fExt);
				fileDto.setfExt(fExt);
				fileDto.setfSize(fUploadFile.getSize());
				fileDto.setrSeq(rSeq);
				
				// DB 저장
				int count = dao.selectOneCount(fileDto);
				
				if (count == 0) {
					dao.insertFile(fileDto);
				} else {
					dao.updateFile(fileDto);
				}
			}
		}
	}
	
}
