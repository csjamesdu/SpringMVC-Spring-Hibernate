package com.csjamesdu.springmvc;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.csjamesdu.springmvc.dto.CategoryDTO;
import com.csjamesdu.springmvc.dto.ProductDTO;
import com.csjamesdu.springmvc.model.Category;
import com.csjamesdu.springmvc.model.Product;
import com.csjamesdu.springmvc.service.CategoryService;
import com.csjamesdu.springmvc.service.ProductService;

@Controller
public class CategoryController {
	//private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	private CategoryService categoryService;
	@SuppressWarnings("unused")
	private ProductService productService;
	
	@Autowired(required=true)
	@Qualifier(value="productService")
	public void setProductService (ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired(required=true)
	@Qualifier(value="categoryService")
	public void setCategoryService (CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="/listcategory", method=RequestMethod.GET)
	public String listCategory(Model model) {
		if(categoryService.listAllCategories().isEmpty()){
			categoryService.addTopCategory();
		}
		model.addAttribute("category", new Category());
		model.addAttribute("categoryList", categoryService.listAllCategories());
		return "listCategory";
	}
	
	@RequestMapping(value="/addcategory")
	public String addCategory(@RequestParam("parent_id")int parent_id, Model model) {		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setParent_id(parent_id);
		model.addAttribute("categoryDTO", categoryDTO);
		return "addCategory";
	}
	
	@RequestMapping(value="/updateaddcategory")
	public String updateAddCategory(@ModelAttribute("categoryDTO")CategoryDTO categoryDTO) {
		Category category = categoryDTO.getCategory();
		int parent_id = categoryDTO.getParent_id();
		categoryService.addChildCategory(category, parent_id);
		return "redirect:listcategory";
	}
	
	@RequestMapping(value="/editcategory")
	public String editCategory(@RequestParam("id")int id, Model model) {
		Category category = new Category();
		category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		return "editCategory";
	}
	
	@RequestMapping(value="/updateeditcategory")
	public String updateEditCategory(@ModelAttribute("category")Category category) {
		Category category_edited = new Category();
		category_edited = categoryService.getCategoryById(category.getId());
		category_edited.setName(category.getName());
		categoryService.updateCategory(category_edited);
		return "redirect:listcategory";
	}
	
	@RequestMapping(value="/deletecategory")
	public String deleteCategory(@RequestParam("id")int id){
		Category category = categoryService.getCategoryById(id);
		if(id == 2){
			categoryService.autoGenerateCategoryTree();
		}else{		
		categoryService.deleteCategoryWithProduct(category);
		}
		return "redirect:listcategory";
	}
	
	@RequestMapping(value="/showproductsundercategory")
	public String showProductUnderCategory(@RequestParam("category_id")int category_id, Model model) {
		List<Product> productList = categoryService.getProductsByCategoryId(category_id);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setCategory_id(category_id);
		model.addAttribute("productList", productList);
		model.addAttribute("product", new Product());
		model.addAttribute("productDTO", productDTO);
		return "listProductUnderCategory";
	}
	
	@RequestMapping(value="/addproductundercategory")
	public String addProductUnderCategory(@ModelAttribute("productDTO")ProductDTO productDTO, Model model){
		Product product = productDTO.getProduct();
		int category_id = productDTO.getCategory_id();		
		categoryService.addProductUnderCategory(product, category_id);
		
		List<Product> productList = categoryService.getProductsByCategoryId(category_id);
		model.addAttribute("productList", productList);
		model.addAttribute("product", new Product());
		return "listProductUnderCategory";
	}
}
