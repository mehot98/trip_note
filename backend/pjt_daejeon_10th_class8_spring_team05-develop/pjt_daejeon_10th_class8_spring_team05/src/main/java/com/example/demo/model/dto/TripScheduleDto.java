package com.example.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class TripScheduleDto {
	public int tripId;
	public int day;
	public int order;
	public int locationId;
}


