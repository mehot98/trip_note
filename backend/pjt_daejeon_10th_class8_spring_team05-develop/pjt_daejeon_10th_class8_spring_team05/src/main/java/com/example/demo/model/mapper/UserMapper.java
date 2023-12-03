package com.example.demo.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.dto.UserDto;

@Mapper
public interface UserMapper {
	

	int idCheck(String userId) throws SQLException;
	int joinUser(UserDto userDto) throws SQLException;
	UserDto login(UserDto userDto) throws SQLException;
//	UserDto getUserProfile(String userId) throws SQLException;
	UserDto userInfo(String userId) throws SQLException;

	// 토큰 관련
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String userId) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	/* Admin */
	List<UserDto> listUser(Map<String, Object> map) throws SQLException;
	UserDto getUser(String userId) throws SQLException;
	int updateUser(UserDto userDto) throws SQLException;
	int deleteUser(String userId) throws SQLException;

}
