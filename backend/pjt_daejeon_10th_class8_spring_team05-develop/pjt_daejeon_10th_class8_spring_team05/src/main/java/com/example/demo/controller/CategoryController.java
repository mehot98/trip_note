package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@GetMapping("/{tripId}")
	public List<String> selectListCategory(@PathVariable("tripId") int tripId) {
//		System.out.println(tripId);
		return categoryService.selectListCategory(tripId);
	}
}
