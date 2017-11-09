package com.csjamesdu.springmvc.dto;

import java.util.List;

import com.csjamesdu.springmvc.model.Product;

public class ProductDTO {
	private List<Product> multiProducts;
	private Product product;
	private int category_id;
	
	public List<Product> getMultiProducts() {
		return multiProducts;
	}

	public void setMultiProducts(List<Product> multiProducts) {
		this.multiProducts = multiProducts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
}
