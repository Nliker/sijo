package com.backend.notification.model.service;

import com.backend.notification.model.NotificationRequestDto;

public interface NotificationService {
	void createNotification(NotificationRequestDto notificationRequest) throws Exception;
}
