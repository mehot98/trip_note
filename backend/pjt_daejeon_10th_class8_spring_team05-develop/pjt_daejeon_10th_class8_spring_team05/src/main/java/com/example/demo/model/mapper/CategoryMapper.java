package com.example.demo.model.mapper;

import java.util.List;

import com.example.demo.model.dto.CategoryDto;

public interface CategoryMapper {
	public int registCategory(CategoryDto categoryDto);
	
	public List<String> selectListCategory(int tripId);
}
