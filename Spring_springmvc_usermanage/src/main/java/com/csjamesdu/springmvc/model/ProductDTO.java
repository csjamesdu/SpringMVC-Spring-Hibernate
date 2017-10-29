package com.csjamesdu.springmvc.model;

import java.util.List;

public class ProductDTO {
	private List<Product> multiProducts;
	
	public List<Product> getMultiProducts() {
		return multiProducts;
	}

	public void setMultiProducts(List<Product> multiProducts) {
		this.multiProducts = multiProducts;
	}
	
}
