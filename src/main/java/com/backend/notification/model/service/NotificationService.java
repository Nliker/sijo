package com.backend.notification.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import com.backend.notification.model.NotificationRequestDto;
import com.backend.notification.model.NotificationResponseDto;
import com.backend.notification.model.NotificationUpdateReqeustDto;

public interface NotificationService {
	void createNotification(NotificationRequestDto notificationRequest, MultipartFile[] files) throws Exception;

	List<NotificationResponseDto> getNotificationList(Map<String, String> map) throws SQLException;

	Integer getNotificationTotalPage(Map<String, String> map) throws SQLException;

	void updateNotification(NotificationUpdateReqeustDto request, MultipartFile[] addList ,int notificationNo);
}
