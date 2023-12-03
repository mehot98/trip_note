package com.example.demo.model.dto;

import lombok.Data;

@Data
public class TripDto {
	public int tripId;
	public String userId;
	public String tripName;
	public String with;
	public String isPublic;
	public byte[] img;
	public String imgName;
	public long imgSize;
}
