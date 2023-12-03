package com.example.demo.model.service;

import com.example.demo.model.dto.LocationDto;

public interface LocationService {

	LocationDto selectLocation(int locationId);

	int registLocation(LocationDto locationDto);
}
