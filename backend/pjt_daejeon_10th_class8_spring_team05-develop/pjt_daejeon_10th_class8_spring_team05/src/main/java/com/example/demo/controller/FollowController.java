package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.FollowDto;
import com.example.demo.model.service.FollowService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/follow")
@Slf4j
public class FollowController {

	private FollowService followService;

	public FollowController(FollowService followService) {
		super();
		this.followService = followService;
	}

	@PostMapping("/user")
	public ResponseEntity<?> followUser(@RequestBody FollowDto followDto) {
		log.debug("followUser followDto : {}", followDto);
		try {
			int isOk = followService.followUser(followDto);
			return new ResponseEntity<Integer>(isOk, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/trip")
	public ResponseEntity<?> followTrip(@RequestBody FollowDto followDto) {
		log.debug("followTrip followDto : {}", followDto);
		try {
			int isOk = followService.followTrip(followDto);
			return new ResponseEntity<Integer>(isOk, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/location")
	public ResponseEntity<?> followLocation(@RequestBody FollowDto followDto) {
		log.debug("followService followDto : {}", followDto);
		try {
			int isOk = followService.followLocation(followDto);
			return new ResponseEntity<Integer>(isOk, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<FollowDto>> getFollowUser(@PathVariable("userId") String userId) throws Exception {
		log.info("userId - 호출 : " + userId);
		followService.getFollowUser(userId);
		return new ResponseEntity<List<FollowDto>>(followService.getFollowUser(userId), HttpStatus.OK);
	}

	@GetMapping("/trip/{userId}")
	public ResponseEntity<List<FollowDto>> getFollowTrip(@PathVariable("userId") String userId) throws Exception {
		log.info("userId - 호출 : " + userId);
		followService.getFollowTrip(userId);
		return new ResponseEntity<List<FollowDto>>(followService.getFollowTrip(userId), HttpStatus.OK);
	}

	@GetMapping("/location/{userId}")
	public ResponseEntity<List<FollowDto>> getFollowLocation(@PathVariable("userId") String userId) throws Exception {
		log.info("userId - 호출 : " + userId);
		followService.getFollowLocation(userId);
		return new ResponseEntity<List<FollowDto>>(followService.getFollowLocation(userId), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/user/{userId}/{followerId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId, @PathVariable("followerId") String followerId) {
		log.debug("userDelete userid : {}", userId);
		try {
			int suc = followService.deleteUser(userId, followerId);
			return new ResponseEntity<Integer>(suc, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping(value = "/trip/{userId}/{tripId}")
	public ResponseEntity<?> deleteTrip(@PathVariable("userId") String userId, @PathVariable("tripId") String tripId) {
		log.debug("userDelete userid : {}", userId);
		try {
			int suc = followService.deleteTrip(userId, tripId);
			return new ResponseEntity<Integer>(suc, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping(value = "/location/{userId}/{locationId}")
	public ResponseEntity<?> deleteLocation(@PathVariable("userId") String userId, @PathVariable("locationId") String locationId) {
		log.debug("userDelete userid : {}", userId);
		try {
			int suc = followService.deleteLocation(userId, locationId);
			return new ResponseEntity<Integer>(suc, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<?> countUser(@PathVariable("userId") String userId){
		log.debug("CountUser userid : {}", userId);
		try {
			int cnt = followService.countUser(userId);
			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		}catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
