package com.example.demo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	private UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
	}

	@Override
	public int joinUser(UserDto userDto) throws Exception {
		return userMapper.joinUser(userDto);		
	}

	@Override
	public UserDto userInfo(String userId) throws Exception {
		return userMapper.userInfo(userId);
	}
	
	
	@Override
	public UserDto login(UserDto userDto) throws Exception {
		return userMapper.login(userDto);
	}
	

	@Override
	public List<UserDto> listUser(Map<String, Object> map) throws Exception {
		return userMapper.listUser(map);
	}

	@Override
	public UserDto getUser(String userId) throws Exception {
		return userMapper.getUser(userId);
	}

	@Override
	public int updateUser(UserDto userDto) throws Exception {
		return userMapper.updateUser(userDto);
	}

	@Override
	public int deleteUser(String userId) throws Exception {
		return userMapper.deleteUser(userId);
		
	}

//	@Override
//	public UserDto getUserProfile(String userId) throws Exception {
//		UserDto user = userMapper.getUser(userId);
//		return user;
//	}
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}

}
