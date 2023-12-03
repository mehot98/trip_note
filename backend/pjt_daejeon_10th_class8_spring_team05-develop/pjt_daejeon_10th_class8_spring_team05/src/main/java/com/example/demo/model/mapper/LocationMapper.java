package com.example.demo.model.mapper;

import com.example.demo.model.dto.LocationDto;

public interface LocationMapper {
	public LocationDto selectLocation(int locationId);
	public int registLocation(LocationDto locationDto);
}
