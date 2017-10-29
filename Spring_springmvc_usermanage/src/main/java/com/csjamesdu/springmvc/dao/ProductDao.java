package com.csjamesdu.springmvc.dao;

import java.util.List;

import com.csjamesdu.springmvc.model.Product;

public interface ProductDao {
	List<Product> list();
	Product loadById(int id);
	void add(Product product);
	void delete(Product product);
	void update(Product product);
}
