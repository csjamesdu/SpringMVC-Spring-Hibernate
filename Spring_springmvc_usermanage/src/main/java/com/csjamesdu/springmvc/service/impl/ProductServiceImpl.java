package com.csjamesdu.springmvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csjamesdu.springmvc.dao.ProductDao;
import com.csjamesdu.springmvc.model.Product;
import com.csjamesdu.springmvc.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final int NUM_AUTO_INSERT=5;
	private ProductDao productDao;
	
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public List<Product> listProduct() {
		return productDao.list();
	}

	@Override
	@Transactional
	public Product loadProductById(int id) {
		return productDao.loadById(id);
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
			Product product = productDao.loadById(id);
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
	
	
}
