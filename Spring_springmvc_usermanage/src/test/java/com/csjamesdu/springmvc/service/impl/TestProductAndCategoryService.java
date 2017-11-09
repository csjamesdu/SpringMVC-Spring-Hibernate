package com.csjamesdu.springmvc.service.impl;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.csjamesdu.springmvc.model.Category;
import com.csjamesdu.springmvc.model.Product;
import com.csjamesdu.springmvc.service.CategoryService;
import com.csjamesdu.springmvc.service.ProductService;
import com.csjamesdu.springmvc.util.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestProductAndCategoryService extends UnitTestBase {

	private static ProductService productService;
	private static CategoryService categoryService;
	
	public TestProductAndCategoryService() {
		super("servlet-context.xml");
	}
	
	@Test
	public void TestCategorySaveTop(){		
		categoryService = super.getBean("categoryService");
		categoryService.addTopCategory();
	}
	
	@Test
	public void TestCategorySaveChild(){		
		categoryService = super.getBean("categoryService");
		categoryService.addTopCategory();
		
		Category category1 = new Category();
		category1.setName("testCat1");
		
		Category category2 = new Category();
		category2.setName("testCat2");
		
		Category category21 = new Category();
		category21.setName("testCat2-1");
		
		Category category22 = new Category();
		category22.setName("testCat2-2");
		
		Category category221 = new Category();
		category221.setName("testCat2-2-1");
		
		Category category2211 = new Category();
		category2211.setName("testCat2-2-1-1");
		
		
		categoryService.addChildCategory(category1, 1);
		categoryService.addChildCategory(category2, 1);
		categoryService.addChildCategory(category21, 3);
		categoryService.addChildCategory(category22, 3);
		categoryService.addChildCategory(category221, 5);
		categoryService.addChildCategory(category2211, 6);
	}
	
	@Test
	public void testAddProductUnderCategory() {
		TestCategorySaveChild();
		productService = super.getBean("productService");
		
		
		Category category = categoryService.getCategoryById(7);
		
		Product product1 = new Product();
		product1.setName("testProduct1");
		product1.setPrice(123);
		
		Product product2 = new Product();
		product2.setName("testProduct2");
		product2.setPrice(1234);
		
		
		productService.addProductUnderCategory(category, product1);	
		productService.addProductUnderCategory(category, product2);
		categoryService.deleteCategoryWithProduct(category);
	}

	@Test
	public void testDeleteCateogryWithProducts() {
		TestCategorySaveChild();
		productService = super.getBean("productService");
		Category category = categoryService.getCategoryById(7);
		
		productService.autoAddProductsUnderCategory(category);
		categoryService.deleteCategoryWithProduct(category);
	}

}
