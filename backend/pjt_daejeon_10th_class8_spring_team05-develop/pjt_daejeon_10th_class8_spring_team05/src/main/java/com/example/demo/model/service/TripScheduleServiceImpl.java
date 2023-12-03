package com.example.demo.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.TripScheduleDto;
import com.example.demo.model.mapper.LocationMapper;
import com.example.demo.model.mapper.TripScheduleMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TripScheduleServiceImpl implements TripScheduleService {
	
	private final TripScheduleMapper tripScheduleMapper;
	private final LocationMapper locationMapper;
	
	public TripScheduleServiceImpl(TripScheduleMapper tripScheduleMapper, LocationMapper locationMapper) {
		super();
		this.tripScheduleMapper = tripScheduleMapper;
		this.locationMapper = locationMapper;
	}

	@Override
	public int registTripSchedule(TripScheduleDto tripScheduleDto) {
		return tripScheduleMapper.registTripSchedule(tripScheduleDto);
	}

	@Override
	public List<TripScheduleDto> selectTripScheduleList(int tripId) {
		return tripScheduleMapper.selectTripScheduleList(tripId);
	}

}
