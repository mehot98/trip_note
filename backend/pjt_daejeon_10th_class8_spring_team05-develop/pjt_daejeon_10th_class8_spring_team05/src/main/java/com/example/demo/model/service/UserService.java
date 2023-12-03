package com.example.demo.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.demo.model.dto.UserDto;

public interface UserService {
	int idCheck(String userId) throws Exception;
	int joinUser(UserDto userDto) throws Exception;
	UserDto login(UserDto userDto) throws Exception;
//	UserDto getUserProfile(String userId) throws Exception;
	
	// 토큰관련
	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	UserDto userInfo(String userId) throws Exception;

	
	/* Admin */
	List<UserDto> listUser(Map<String, Object> map) throws Exception;
	UserDto getUser(String userId) throws Exception;
	int updateUser(UserDto userDto) throws Exception;
	int deleteUser(String userId) throws Exception;
}
