package com.csjamesdu.springmvc.dto;

import com.csjamesdu.springmvc.model.Category;

public class CategoryDTO {
	Category category;
	int parent_id;
	
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
