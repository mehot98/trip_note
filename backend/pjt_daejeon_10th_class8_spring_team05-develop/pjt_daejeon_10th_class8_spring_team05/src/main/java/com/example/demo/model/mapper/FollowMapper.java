package com.example.demo.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.dto.FollowDto;


@Mapper
public interface FollowMapper {

//	// follow한 목록 넣기
//	int followUser(Map<String, String> param) throws SQLException;
//	int followTrip(Map<Integer, String> param) throws SQLException;
//	int followLocation(Map<String, Integer> param) throws SQLException;

	int followUser(FollowDto follow) throws SQLException;
	int followTrip(FollowDto follow) throws SQLException;
	int followLocation(FollowDto follow) throws SQLException;
	
	// follow 목록 가져오기 => 이거 가지고 controller에서 count 할 예정 
	List<FollowDto> getFollowUser(String userId) throws SQLException;
	List<FollowDto> getFollowTrip(String userId) throws SQLException;
	List<FollowDto> getFollowLocation(String userId) throws SQLException;
	
	// follow 취소하기
	int deleteUser(String userId, String followerId) throws SQLException;
	int deleteTrip(String userId, String tripId) throws SQLException;
	int deleteLocation(String userId, String locationId) throws SQLException;
	
	// follow 수 세기
	int countUser(String userId) throws SQLException;
}
