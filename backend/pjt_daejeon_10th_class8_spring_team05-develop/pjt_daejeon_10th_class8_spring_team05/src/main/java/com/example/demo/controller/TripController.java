package com.example.demo.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.dto.LocationDto;
import com.example.demo.model.dto.TripDto;
import com.example.demo.model.dto.TripScheduleDto;
import com.example.demo.model.service.CategoryService;
import com.example.demo.model.service.LocationService;
import com.example.demo.model.service.TripScheduleService;
import com.example.demo.model.service.TripService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/trip")
@CrossOrigin("*")
@Api(tags = "여행 컨트롤러")
public class TripController {
	private final TripService tripService;
	private final CategoryService categoryService;
	private final LocationService locationService;
	private final TripScheduleService tripScheduleService;
	
	public TripController(TripService tripService, CategoryService categoryService, LocationService locationService, TripScheduleService tripScheduleService) {
		super();
		this.tripService = tripService;
		this.categoryService = categoryService;
		this.locationService = locationService;
		this.tripScheduleService = tripScheduleService;
	}

	@GetMapping("")
	@ApiOperation(value = "여행 정보 목록 조회", notes = "여행 정보 목록을 가져옵니다.")
	public List<TripDto> tripList(@RequestParam(name="page", defaultValue = "0")int page, @RequestParam(name="userId", required=false)String userId) {
		int startPage = page == 0 ? 0 : (page-1) * 5;
		int endPage = page == 0 ? tripService.getLastId() : startPage + 5;
		return tripService.selectTripList(startPage, endPage, userId);
	}
	
	@GetMapping("/{tripId}")
	@ApiOperation(value = "여행 정보 조회", notes = "여행 정보 하나를 가져옵니다.")
	public TripDto selectTrip(@PathVariable int tripId) {
		TripDto result = tripService.selectTrip(tripId);
		return result;
	}
	
	@GetMapping("/detail/{tripId}")
	@ApiOperation(value = "여행 정보 조회", notes = "여행 정보 하나를 가져옵니다.")
	public Map<String, Object> selectTripDetail(@PathVariable int tripId) {
		
		Map<String, Object> result = new HashMap();
		
		TripDto tripDto = tripService.selectTrip(tripId);
		
		result.put("tripId", tripId);
		result.put("userId", tripDto.getUserId());
		result.put("tripName", tripDto.getTripName());
		result.put("with", tripDto.getWith());
		result.put("isPublic", tripDto.getIsPublic());
		result.put("img", tripDto.getImg());
		result.put("category", categoryService.selectListCategory(tripId));
		List<TripScheduleDto> tripScheduleDtoList = tripScheduleService.selectTripScheduleList(tripId);
		System.out.println(tripScheduleDtoList);
		List<List<LocationDto>> locations = new ArrayList();
		locations.add(new ArrayList());
		for(int i = 0; i < tripScheduleDtoList.size(); i++) {
			TripScheduleDto tripScheduleDto = tripScheduleDtoList.get(i);
			if(tripScheduleDto.getDay() >= locations.size())
				locations.add(new ArrayList());
			System.out.println(locationService.selectLocation(tripScheduleDto.getLocationId()));
			locations.get(tripScheduleDto.getDay()).add(locationService.selectLocation(tripScheduleDto.getLocationId()));
		}
		result.put("locations", locations);
		
		return result;
//		TripDto result = tripService.selectTrip(tripId);
	}
	
	@PostMapping(value="", consumes="multipart/form-data")
	@ApiOperation(value = "여행 등록", notes = "여행 정보를 등록합니다.")
	public int registTrip(@RequestPart(required=false) MultipartFile img, @RequestPart(value="info") Map<String, Object> map) throws SerialException, SQLException, IOException {

		// regist trip
		int tripId = tripService.getLastId()+1;
		TripDto tripDto = new TripDto();
		tripDto.setTripId(tripId);
		tripDto.setUserId((String)map.get("userId"));
		tripDto.setTripName((String)map.get("tripName"));
		if(tripDto.getTripName() == "") tripDto.setTripName("재미있는 여행~");
		tripDto.setWith((String)map.get("with"));
		tripDto.setIsPublic((String)map.get("isPublic"));
		
		if(img != null) {
			String fileName = img.getOriginalFilename();
			String extension = fileName.substring(fileName.length()-3, fileName.length()).toLowerCase();
			
			Blob blob = new SerialBlob(img.getBytes());
			
			if(extension.equals("jpg") || extension.equals("png")) {
				tripDto.setImg(img.getBytes());
				tripDto.setImgName(fileName);
				tripDto.setImgSize(blob.length());
			}
		}
		
		tripService.registTrip(tripDto);
		
		
		// regist categoty
		List<String> categories = (ArrayList)map.get("category");
		for(int i = 0; i < categories.size(); i++) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setTripId(tripId);
			categoryDto.setCategory(categories.get(i));
			categoryService.registCategory(categoryDto);
		}
		
		
		
		// regist location
		List<List<Map<String, String>>> data = (List<List<Map<String, String>>>) map.get("data");
		
		for(int i = 1; i < data.size(); i++) {
			for(int j = 0; j < data.get(i).size(); j++) {
				Map<String, String> locationData = data.get(i).get(j);
				LocationDto locationDto = new LocationDto();
				locationDto.setLocationId(Integer.parseInt(locationData.get("id")));
				locationDto.setX(Double.parseDouble(locationData.get("x")));
				locationDto.setY(Double.parseDouble(locationData.get("y")));
				locationDto.setPlaceUrl(locationData.get("place_url"));
				locationDto.setPlaceName(locationData.get("place_name"));
				locationDto.setAddressName(locationData.get("address_name"));
				locationService.registLocation(locationDto);
				
				//regist tripSchedule
				TripScheduleDto tripScheduleDto = new TripScheduleDto();
				tripScheduleDto.setTripId(tripId);
				tripScheduleDto.setDay(i);
				tripScheduleDto.setOrder(j);
				tripScheduleDto.setLocationId(locationDto.getLocationId());
				tripScheduleService.registTripSchedule(tripScheduleDto);
				
			}
			
		}
		
		return 1;
	}

}
