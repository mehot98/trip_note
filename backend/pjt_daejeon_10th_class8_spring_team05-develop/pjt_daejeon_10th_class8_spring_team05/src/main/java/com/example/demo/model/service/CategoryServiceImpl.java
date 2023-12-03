package com.example.demo.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CategoryDto;
import com.example.demo.model.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService{
	private final CategoryMapper categoryMapper;
	
	public CategoryServiceImpl(CategoryMapper categoryMapper) {
		super();
		this.categoryMapper = categoryMapper;
	}



	@Override
	public int registCategory(CategoryDto categoryDto) {
		return categoryMapper.registCategory(categoryDto);
	}



	@Override
	public List<String> selectListCategory(int tripId) {
		return categoryMapper.selectListCategory(tripId);
	}

}
