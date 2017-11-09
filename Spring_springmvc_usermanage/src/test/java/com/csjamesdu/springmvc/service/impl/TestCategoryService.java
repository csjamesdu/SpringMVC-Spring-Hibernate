package com.csjamesdu.springmvc.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.csjamesdu.springmvc.model.Category;
import com.csjamesdu.springmvc.service.CategoryService;
import com.csjamesdu.springmvc.util.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestCategoryService extends UnitTestBase{
	private static CategoryService service;
	
	public TestCategoryService(){
		super("servlet-context.xml");		
	}
	
	
	@Test
	public void TestCategorySaveTop(){		
		service = super.getBean("categoryService");
		service.addTopCategory();
	}
	
	@Test
	public void TestCategorySaveChild(){		
		service = super.getBean("categoryService");
		service.addTopCategory();
		
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
		
		
		service.addChildCategory(category1, 2);
		service.addChildCategory(category2, 2);
		service.addChildCategory(category21, 4);
		service.addChildCategory(category22, 4);
		service.addChildCategory(category221, 6);
		service.addChildCategory(category2211, 7);
	}
	
	@Test
	public void TestListAllCategories() {
		TestCategorySaveChild();
		List<Category> categories = service.listAllCategories();
		for(Category category: categories){
			System.out.println(category.getName());
		}		
	}
	
	@Test
	public void TestDeleteCategoryWithBiz() {  //works but not perfect!
		TestCategorySaveChild();
		
		service = super.getBean("categoryService");
		Category category = service.getCategoryById(4);
		int parent_id = category.getParent().getId();
		service.deleteCategory(category);
		service.updateParentAfterDelete(service.getCategoryById(parent_id));
	
	}
	
	@Test
	public void TestDeleteLeafCategory() {
		TestCategorySaveChild();
		
		service = super.getBean("categoryService");
		Category category = service.getCategoryById(4);
		service.deleteCategory(category);
	
	}
	
	@Test
	public void TestDeleteNonLeafCategory() {
		TestCategorySaveChild();
		
		service = super.getBean("categoryService");
		Category category = service.getCategoryById(4);
		service.deleteCategory(category);
	
	}
	
	@Test
	public void TestAutoGen() {
		service = super.getBean("categoryService");
		service.addTopCategory();
		service.autoGenerateCategoryTree();
	}
}
