package com.example.demo.model.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.dto.TripDto;

public interface TripService {
	public List<TripDto> selectTripList(int startPage, int endPage, String userId);
//
	public TripDto selectTrip(int tripId);

	public int registTrip(TripDto tripDto);

//	public int updateTrip(TripDto tripDto);
//
//	public int deleteTrip(String tripId);
//
//	public int selectTripFollow(String userId, int tripId);
//
//	public int registTripFollow(String userId, int tripId);
//
//	public int deleteTripFollow(String userId, int tripId);
//	
//	public List<TripDto> selectUserFollowTripList(String userId);
//	
//	public List<UserDto> selectTripFollowUserList(int tripId);
//	
//	public int selectTripFollowUserCount(int tripId);
//	
//	public int selectUserFollowTripCount(String userId);
//	
	public int getLastId();
	
	public Map<String, Object> selectImg();
}
