package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.dto.CategoryDto;

public interface CategoryService {
	int registCategory(CategoryDto categoryDto);
	List<String> selectListCategory(int tripId);
}
