package com.example.demo.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.TripDto;
import com.example.demo.model.mapper.TripMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TripServiceImpl implements TripService{
	private final TripMapper tripMapper;

	public TripServiceImpl(TripMapper tripMapper) {
		super();
		this.tripMapper = tripMapper;
	}
	
	@Override
	public List<TripDto> selectTripList(int startPage, int endPage, String userId) {
		return tripMapper.selectTripList(startPage, endPage, userId);
	}

	@Override
	public TripDto selectTrip(int tripId) {
		return tripMapper.selectTrip(tripId);
	}

	@Override
	public int registTrip(TripDto tripDto) {
		return tripMapper.registTrip(tripDto);
	}

	@Override
	public int getLastId() {
		try {
			return tripMapper.getLastId();
		} catch(Exception e) {
			log.error("locationServiceImpl, maybe location table is empty");
			System.out.println(e);
		}
		return 0;
	}

	@Override
	public Map<String, Object> selectImg() {
		// TODO Auto-generated method stub
		return tripMapper.selectImg();
	}



}
