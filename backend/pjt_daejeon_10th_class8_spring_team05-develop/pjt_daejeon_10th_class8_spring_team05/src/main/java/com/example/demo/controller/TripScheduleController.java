package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.TripScheduleDto;
import com.example.demo.model.service.TripScheduleService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/trip-schedule")
@CrossOrigin("*")
@Api(tags = "여행 스케쥴 컨트롤러")
public class TripScheduleController {
	private final TripScheduleService tripScheduleService;
	
	public TripScheduleController(TripScheduleService tripScheduleService) {
		super();
		this.tripScheduleService = tripScheduleService;
	}

//	@GetMapping("/{tripId}")
//	public List<TripScheduleDto> selectTripScheduleList (@PathVariable int tripId) {
//		return tripScheduleService.selectTripScheduleList(tripId);
//	}
	
}
