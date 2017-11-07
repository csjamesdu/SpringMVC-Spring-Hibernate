package com.csjamesdu.springmvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csjamesdu.springmvc.dao.CategoryDao;
import com.csjamesdu.springmvc.dao.ProductDao;
import com.csjamesdu.springmvc.model.Category;
import com.csjamesdu.springmvc.model.Product;
import com.csjamesdu.springmvc.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final int NUM_AUTO_INSERT=5;
	private ProductDao productDao;
	private CategoryDao categoryDao;
	
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	@Transactional
	public List<Product> listProduct() {
		return productDao.list();
	}

	@Override
	@Transactional
	public Product getProductById(int id) {
		return productDao.getById(id);
	}

	@Override
	@Transactional
	public void addProduct(Product product) {
		productDao.add(product);
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		productDao.delete(product);
	}
	
	@Override
	@Transactional
	public void deleteMultiProducts(int[] products_id){
		for(int id:products_id){
			Product product = productDao.getById(id);
			productDao.delete(product);
		}	
	}

	@Override
	@Transactional
	public void editProduct(Product product) {
		productDao.update(product);
	}

	@Override
	@Transactional
	public void autoinsertProduct() {
		for(int i=0;i<NUM_AUTO_INSERT;i++) {
			Product product = new Product();
			product.setName("Auto: " + i);
			product.setPrice(i*1);
			productDao.add(product);
		}		
	}

	@Override
	@Transactional
	public void autoinsertrbProduct() {
		for(int i=0;i<NUM_AUTO_INSERT;i++){
			if(i==3){
				throw new RuntimeException();
			}
			Product product = new Product();
			product.setName("Auto:" + i);
			product.setPrice(i*10);
			productDao.add(product);
		}
	}
	
	@Override
	@Transactional
	public void addProductUnderCategory(Category category, Product product) {
		product.setCategory(category);
		category.getProducts().add(product);
		productDao.add(product);
	}
	
	@Override
	@Transactional
	public void autoAddProductsUnderCategory(Category category) {
		for(int i=0; i<NUM_AUTO_INSERT; i++) {
			Product product = new Product();
			product.setName("AutoInsert " + i);
			product.setPrice(i*10);
			product.setCategory(category);
			category.getProducts().add(product);
			productDao.add(product);
		}
	}
	
	@Override
	@Transactional
	public void updateProductAndCategory(Category category, Product product) {
		categoryDao.update(category);
		productDao.update(product);
	}
	
	
}
