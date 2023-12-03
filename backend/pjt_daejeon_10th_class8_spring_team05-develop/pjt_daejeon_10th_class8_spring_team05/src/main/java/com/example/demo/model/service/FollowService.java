package com.example.demo.model.service;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.model.dto.FollowDto;

public interface FollowService {
    int followUser(FollowDto follow) throws Exception;
    int followTrip(FollowDto follow) throws Exception;
    int followLocation(FollowDto follow) throws Exception;
    
    List<FollowDto> getFollowUser(String userId) throws Exception;
	List<FollowDto> getFollowTrip(String userId) throws Exception;
	List<FollowDto> getFollowLocation(String userId) throws Exception;
	
	int deleteUser(String userId, String followerId) throws Exception;
	int deleteTrip(String userId, String tripId) throws Exception;
	int deleteLocation(String userId, String locationId) throws Exception;
	
	int countUser(String userId) throws SQLException;
}
