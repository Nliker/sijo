package com.backend.notification.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.backend.notification.model.NotificationDto;
import com.backend.notification.model.NotificationFileDto;
import com.backend.notification.model.NotificationRequestDto;
import com.backend.notification.model.mapper.NotificationMapper;
import com.backend.s3.service.S3ServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
	private final NotificationMapper notificationMapper;
	private final S3ServiceImpl s3Service;
	
	@Transactional
	@Override
	public void createNotification(NotificationRequestDto notificationRequest) throws Exception {
		NotificationDto notification=new NotificationDto(notificationRequest);
		notificationMapper.insertNotification(notification);
		for(int i=0;i<notificationRequest.getFileList().length;i++) {
			NotificationFileDto notificationFile=new NotificationFileDto();
			notificationFile.setNotificationNo(notification.getNotificationNo());
			
			MultipartFile file=notificationRequest.getFileList()[i];
			notificationFile.setFileName(file.getOriginalFilename());
			notificationFile.setFileUri(s3Service.uploadFile(file));
			notificationFile.setPosition(i);
			notificationMapper.insertNotificationFile(notificationFile);
		}
		
	}

}
