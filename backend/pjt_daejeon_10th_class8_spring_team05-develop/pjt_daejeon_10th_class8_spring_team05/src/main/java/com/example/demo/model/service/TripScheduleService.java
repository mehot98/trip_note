package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.dto.TripScheduleDto;

public interface TripScheduleService {
	public int registTripSchedule(TripScheduleDto tripScheduleDto);
	public List<TripScheduleDto> selectTripScheduleList(int tripId);
}
