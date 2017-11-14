package com.csjamesdu.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csjamesdu.springmvc.dao.CategoryDao;
import com.csjamesdu.springmvc.dao.ProductDao;
import com.csjamesdu.springmvc.model.Category;
import com.csjamesdu.springmvc.model.Product;
import com.csjamesdu.springmvc.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	private CategoryDao categoryDao;
	private ProductDao productDao;
	
	public void setCategoryDao (CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public void setProductDao (ProductDao productDao) {
		this.productDao = productDao;
	}	
	
	@Override
	@Transactional
	public void addTopCategory(){
		Category parent = new Category();
		parent.setId(0);
		parent.setName("default");
		categoryDao.add(parent);
		Category top_category = new Category();
		top_category.setName("RootCategory");
		top_category.setIsleaf(1);
		top_category.setGrade(1);
		top_category.setParent(parent);
		categoryDao.add(top_category);		
	}

	@Override
	@Transactional
	public void addChildCategory(Category category, int parent_id) {		
		
		Category child_category = new Category();
		Category parent_category = categoryDao.getById(parent_id);
				
		child_category.setName(category.getName());
		child_category.setIsleaf(1);
		child_category.setGrade(parent_category.getGrade()+1);
		child_category.setParent(parent_category);		
		categoryDao.add(child_category);
		
		parent_category.setIsleaf(0);
		parent_category.getChildren().add(child_category);
		categoryDao.update(parent_category);
	}
	
	@Override
	@Transactional
	public void addChildCategoryByCategory(Category category, Category parent_category) {
		Category child_category = new Category();
		Set<Category> children_categories = parent_category.getChildren();
		
		child_category.setName(category.getName());
		child_category.setIsleaf(1);
		child_category.setGrade(parent_category.getGrade()+1);
		child_category.setParent(parent_category);	
		children_categories.add(child_category);
		categoryDao.add(child_category);
		
		if(parent_category.getIsleaf()==1){
			parent_category.setIsleaf(0);
			categoryDao.update(parent_category);
		}		
		
	}
	
	
	@Override
	@Transactional
	public void deleteCategoryWithProduct(Category category) {
		Set<Product> products = category.getProducts();
		
		if(products.isEmpty()){
			deleteCategory(category);
		}else{
			for(Product product : products) {
				productDao.deleteWithNewSession(product);
			}
			deleteCategory(category);
		}
	}
	
	@Override
	@Transactional
	public void deleteRootCategory(Category category){
		categoryDao.delete(category);
	}

	@Override
	@Transactional
	public void deleteCategory(Category category) {	
		Category parent_category = category.getParent();
		int parent_id = parent_category.getId();
		
		if(category.getIsleaf()== 1){			
			categoryDao.deleteLeaf(category);
			Category parent_category_reload = categoryDao.getById(parent_id);
			updateParentAfterDelete(parent_category_reload);
		}else{			
			categoryDao.deleteNonLeaf(category);
			Category parent_category_reload = categoryDao.getById(parent_id);
			updateParentAfterDelete(parent_category_reload);
		}		
	}
	
	
	@Override
	@Transactional
	public void updateParentAfterDelete(Category category) {
		if(category.getChildren().isEmpty()) {
			category.setIsleaf(1);
			categoryDao.update(category);
		}		
	}
	
	@Override
	@Transactional
	public List<Category> listAllCategories() {
		List<Category> categories = new ArrayList<>();
		categoryDao.listCategoryTree(categories,1);
		return categories;
	}
	
	@Override
	@Transactional
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}
	
	@Override
	@Transactional
	public Category getCategoryById(int id) {
		return categoryDao.getById(id);
	}
	
	@Override
	@Transactional
	public Category loadCategoryById(int id) {
		return categoryDao.loadById(id);
	}	
	
	@Override
	@Transactional
	public void autoGenerateCategoryTree() {		
		Category category1 = new Category();
		category1.setName("Category1");
		addChildCategory(category1, 2);
		Category category2 = new Category();
		category2.setName("Category2");
		addChildCategory(category2, 2);
	}

	@Override
	@Transactional
	public List<Product> getProductsByCategoryId(int category_id) {
		List<Product> productList = new ArrayList<>();
		Category category = categoryDao.getById(category_id);
		Set<Product> productSet = category.getProducts();
		for(Product p : productSet) {
			productList.add(p);
		}		
		return productList;
	}

	@Override
	@Transactional
	public void addProductUnderCategory(Product product, int category_id) {
		Product product_add = new Product();
		Category category = categoryDao.getById(category_id);
		
		product_add.setName(product.getName());
		product_add.setPrice(product.getPrice());
		product_add.setCategory(category);
		category.getProducts().add(product_add);
		
		productDao.add(product_add);
	}
	
}
