package com.backend.notification.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.backend.notification.model.NotificationDto;
import com.backend.notification.model.NotificationFileDto;
import com.backend.notification.model.NotificationJoinFileDto;

@Mapper
public interface NotificationMapper {
	void insertNotification(NotificationDto notification) throws SQLException;
	void insertNotificationFile(NotificationFileDto notificationFile) throws SQLException;
	List<NotificationDto> selectNotification(Map<String, Object> param) throws SQLException;
	int selectNotificationCount(Map<String, String> param) throws SQLException;
	NotificationDto selectNotificationByNo(int notificationNo) throws SQLException;
	void updateNotificationByNo(NotificationDto updateNotification) throws SQLException;
	void deleteNotificationFile(String deleteId) throws SQLException;
	int selectNotificationFileMaxPosition(int notificationNo) throws SQLException;
	NotificationJoinFileDto selectNotificationJoinFileByNo(int notificationNo) throws SQLException;
}
