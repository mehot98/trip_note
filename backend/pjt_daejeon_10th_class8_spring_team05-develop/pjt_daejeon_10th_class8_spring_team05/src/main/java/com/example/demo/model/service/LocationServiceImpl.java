package com.example.demo.model.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.LocationDto;
import com.example.demo.model.mapper.LocationMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService{
	private final LocationMapper locationMapper;

	public LocationServiceImpl(LocationMapper locationMapper) {
		super();
		this.locationMapper = locationMapper;
	}

	@Override
	public LocationDto selectLocation(int locationId) {
		return locationMapper.selectLocation(locationId);
	}

	@Override
	public int registLocation(LocationDto locationDto) {
		if(locationMapper.selectLocation(locationDto.getLocationId()) == null)
			return locationMapper.registLocation(locationDto);
		return -1;
	}
}
