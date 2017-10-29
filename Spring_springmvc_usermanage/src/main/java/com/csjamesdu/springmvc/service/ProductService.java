package com.csjamesdu.springmvc.service;

import java.util.List;

import com.csjamesdu.springmvc.model.Product;

public interface ProductService {
	List<Product> listProduct();
	Product loadProductById(int id);
	void addProduct(Product product);
	void deleteProduct(Product product);
	void editProduct(Product product);
	void autoinsertProduct();
	void autoinsertrbProduct();
	void deleteMultiProducts(int[] products_id);
}
