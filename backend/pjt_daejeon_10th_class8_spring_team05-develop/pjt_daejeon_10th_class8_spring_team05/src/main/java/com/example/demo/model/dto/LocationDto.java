package com.example.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LocationDto {
	public int locationId;
	public double x;
	public double y;
	public String placeUrl;
	public String placeName;
	public String addressName;
}


