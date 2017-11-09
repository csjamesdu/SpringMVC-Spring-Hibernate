package com.csjamesdu.springmvc.service;

import java.util.List;

import com.csjamesdu.springmvc.model.Product;
import com.csjamesdu.springmvc.model.Category;

public interface CategoryService {
	
	void addTopCategory();
	
	void addChildCategory(Category category, int parent_id);
	
	void deleteCategory(Category category);
	
	List<Category> listAllCategories();

	Category getCategoryById(int id);
	
	Category loadCategoryById(int id);
	
	void updateParentAfterDelete(Category category);

	void updateCategory(Category category);

	void deleteCategoryWithProduct(Category category);

	void autoGenerateCategoryTree();

	void deleteRootCategory(Category category);

	void addChildCategoryByCategory(Category category, Category parent_category);

	List<Product> getProductsByCategoryId(int category_id);

	void addProductUnderCategory(Product product, int category_id);
	
}
