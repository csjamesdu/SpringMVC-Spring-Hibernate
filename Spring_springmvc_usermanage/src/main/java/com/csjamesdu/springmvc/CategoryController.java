package com.csjamesdu.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.csjamesdu.springmvc.service.CategoryService;

@Controller
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	private CategoryService categoryService;
		
	public void setCategoryService (CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
}
