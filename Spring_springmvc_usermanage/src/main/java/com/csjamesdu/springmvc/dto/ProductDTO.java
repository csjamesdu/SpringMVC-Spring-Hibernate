package com.csjamesdu.springmvc.dto;

import java.util.List;

import com.csjamesdu.springmvc.model.Product;

public class ProductDTO {
	private List<Product> multiProducts;
	
	public List<Product> getMultiProducts() {
		return multiProducts;
	}

	public void setMultiProducts(List<Product> multiProducts) {
		this.multiProducts = multiProducts;
	}
	
}
