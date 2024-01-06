package com.backend.notification.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.backend.academy.model.AcademyDto;
import com.backend.academy.model.AcademyResponseDto;
import com.backend.errorcode.AcademyErrorCode;
import com.backend.errorcode.NotificationErrorCode;
import com.backend.exception.AcademyException;
import com.backend.exception.NotificationException;
import com.backend.gallery.model.GalleryJoinMediaDto;
import com.backend.notification.model.NotificationDto;
import com.backend.notification.model.NotificationFileDto;
import com.backend.notification.model.NotificationJoinFileDto;
import com.backend.notification.model.NotificationRequestDto;
import com.backend.notification.model.NotificationResponseDto;
import com.backend.notification.model.NotificationUpdateReqeustDto;
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
	
	@Value("${notificationDefaultPage}")
	private int notificationDefaultPage;
	
	@Transactional
	@Override
	public void createNotification(NotificationRequestDto notificationRequest,MultipartFile[] files) throws Exception {
		NotificationDto notification=new NotificationDto(notificationRequest);
		notificationMapper.insertNotification(notification);
		for(int i=0;i<files.length;i++) {
			NotificationFileDto notificationFile=new NotificationFileDto();
			notificationFile.setNotificationNo(notification.getNotificationNo());
			
			MultipartFile file=files[i];
			if(file.isEmpty()) {
				continue;
			}
			notificationFile.setFileName(file.getOriginalFilename());
			notificationFile.setFileUri(s3Service.uploadFile(file));
			notificationFile.setPosition(i+1);
			notificationMapper.insertNotificationFile(notificationFile);
		}
	}

	@Override
	public List<NotificationResponseDto> getNotificationList(Map<String, String> map) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", (Integer.parseInt(map.getOrDefault("page","1"))-1)*notificationDefaultPage);
		param.put("count",Integer.parseInt(map.getOrDefault("count", String.valueOf(notificationDefaultPage))));
		
		param.put("title",map.getOrDefault("title", ""));
		param.put("order", map.getOrDefault("order","latest"));
		
		List<NotificationDto> notificationList=notificationMapper.selectNotification(param);
		List<NotificationResponseDto> notificationResponseList=new ArrayList<>();
		for(NotificationDto notification:notificationList) {
			System.out.println(notification);
			notificationResponseList.add(new NotificationResponseDto(notification));
		}
		return notificationResponseList;
	}

	@Override
	public Integer getNotificationTotalPage(Map<String, String> map) throws Exception{
		Map<String, String> param = new HashMap<String, String>();
		param.put("title", map.getOrDefault("title", ""));
		return (int) Math.max(Math.ceil(((double)notificationMapper.selectNotificationCount(param))/notificationDefaultPage),1);
	}

	@Override
	public void updateNotification(NotificationUpdateReqeustDto request, MultipartFile[] addList, int notificationNo) throws Exception {
		NotificationDto notification=notificationMapper.selectNotificationByNo(notificationNo);
		if(notification==null) {
			throw new NotificationException(NotificationErrorCode.NotFoundNotification.getCode(),NotificationErrorCode.NotFoundNotification.getDescription());
		}
		NotificationDto updateNotification=new NotificationDto(request);
		updateNotification.setNotificationNo(notificationNo);
		notificationMapper.updateNotificationByNo(updateNotification);
		for(String deleteId:request.getDeleteList()) {
			s3Service.deleteFile(deleteId);
		}
		
		int curMaxPosition=notificationMapper.selectNotificationFileMaxPosition(notificationNo)+1;
		for(int i=0;i<addList.length;i++) {
			NotificationFileDto notificationFile=new NotificationFileDto();
			notificationFile.setNotificationNo(notificationNo);
			
			MultipartFile file=addList[i];
			if(file.isEmpty()) {
				continue;
			}
			notificationFile.setFileName(file.getOriginalFilename());
			notificationFile.setFileUri(s3Service.uploadFile(file));
			notificationFile.setPosition(curMaxPosition+i);
			notificationMapper.insertNotificationFile(notificationFile);
		}
	}

	@Override
	public NotificationJoinFileDto getNotification(int notificationNo) throws Exception {
		NotificationDto notification=notificationMapper.selectNotificationByNo(notificationNo);
		if(notification==null) {
			throw new NotificationException(NotificationErrorCode.NotFoundNotification.getCode(),NotificationErrorCode.NotFoundNotification.getDescription());
		}
		
		NotificationJoinFileDto notificationJoinFile=notificationMapper.selectNotificationJoinFileByNo(notificationNo);
		log.debug("notificationJoinFile: "+notificationJoinFile);
		
		return notificationJoinFile;
	}

}
