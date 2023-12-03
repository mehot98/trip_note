package com.example.demo.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.FollowDto;
import com.example.demo.model.mapper.FollowMapper;

@Service
public class FollowServiceImpl implements FollowService {
	
	private FollowMapper followMapper;
	
	public FollowServiceImpl(FollowMapper followMapper) {
		this.followMapper = followMapper;
	}

	@Override
	public int followUser(FollowDto follow) throws Exception {
		return followMapper.followUser(follow);
	}

	@Override
	public int followTrip(FollowDto follow) throws Exception {
		return followMapper.followTrip(follow);
	}

	@Override
	public int followLocation(FollowDto follow) throws Exception {
		return followMapper.followLocation(follow);
	}

	@Override
	public List<FollowDto> getFollowUser(String userId) throws Exception {
		return followMapper.getFollowUser(userId);
	}

	@Override
	public List<FollowDto> getFollowTrip(String userId) throws Exception {
		return followMapper.getFollowTrip(userId);
	}

	@Override
	public List<FollowDto> getFollowLocation(String userId) throws Exception {
		return followMapper.getFollowLocation(userId);
	}

	@Override
	public int deleteUser(String userId, String followerId) throws Exception {
		return followMapper.deleteUser(userId, followerId);
	}

	@Override
	public int deleteTrip(String userId, String tripId) throws Exception {
		return followMapper.deleteTrip(userId, tripId);
	}

	@Override
	public int deleteLocation(String userId, String locationId) throws Exception {
		return followMapper.deleteLocation(userId, locationId);
	}

	@Override
	public int countUser(String userId) throws SQLException {
		return followMapper.countUser(userId);
	}

}
