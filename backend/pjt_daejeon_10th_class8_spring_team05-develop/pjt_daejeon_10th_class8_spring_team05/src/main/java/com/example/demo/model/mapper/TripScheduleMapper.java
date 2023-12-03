package com.example.demo.model.mapper;

import java.util.List;

import com.example.demo.model.dto.TripScheduleDto;

public interface TripScheduleMapper {
	public int registTripSchedule(TripScheduleDto tripScheduleDto);
	public List<TripScheduleDto> selectTripScheduleList(int tripId);
}
