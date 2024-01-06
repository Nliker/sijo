package com.backend.notification.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.backend.notification.model.NotificationJoinFileDto;
import com.backend.notification.model.NotificationRequestDto;
import com.backend.notification.model.NotificationResponseDto;
import com.backend.notification.model.NotificationUpdateReqeustDto;

public interface NotificationService {
	void createNotification(NotificationRequestDto notificationRequest, MultipartFile[] files) throws Exception;

	List<NotificationResponseDto> getNotificationList(Map<String, String> map) throws Exception;

	Integer getNotificationTotalPage(Map<String, String> map) throws Exception;

	void updateNotification(NotificationUpdateReqeustDto request, MultipartFile[] addList ,int notificationNo) throws Exception;

	NotificationJoinFileDto getNotification(int notificationNo) throws Exception;

	void deleteNotification(int notificationNo)  throws Exception;
}
