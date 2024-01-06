package com.backend.notification.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.backend.notification.model.NotificationDto;
import com.backend.notification.model.NotificationFileDto;

@Mapper
public interface NotificationMapper {
	void insertNotification(NotificationDto notification) throws SQLException;
	void insertNotificationFile(NotificationFileDto notificationFile) throws SQLException;
}
