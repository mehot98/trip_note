package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.LocationDto;
import com.example.demo.model.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/location")
@CrossOrigin("*")
@Api(tags = "여행지 컨트롤러")
public class LocationController {
	private final LocationService locationService;
	
	public LocationController(LocationService locationService) {
		super();
		this.locationService = locationService;
	}

	@GetMapping("/{locationId}")
	@ApiOperation(value = "여행지 정보 목록 조회", notes = "여행지 정보 목록을 가져옵니다.")
	public LocationDto LocationList(@PathVariable int locationId) {
		return locationService.selectLocation(locationId);
	}

	@PostMapping("")
	@ApiOperation(value = "여행지 등록", notes = "여행지 정보를 등록합니다.")
	public int registLocation(LocationDto locationDto) {
		return locationService.registLocation(locationDto);
	}
}
