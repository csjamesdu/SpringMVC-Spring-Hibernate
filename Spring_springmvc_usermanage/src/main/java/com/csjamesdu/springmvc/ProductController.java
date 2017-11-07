package com.csjamesdu.springmvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csjamesdu.springmvc.dto.ProductDTO;
import com.csjamesdu.springmvc.model.Product;
import com.csjamesdu.springmvc.service.ProductService;

@Controller
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;
	
	@Autowired(required=true)
	@Qualifier(value="productService")
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value="/listproduct", method=RequestMethod.GET)
	public String listProduct(Model model){
		model.addAttribute("product", new Product());
		model.addAttribute("productList", productService.listProduct());
		return "listProduct";
	}
	
	@RequestMapping(value="/addproduct", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product")Product product){
		productService.addProduct(product);
		return "redirect:/listproduct";
	}
	
	@RequestMapping(value="/editproduct")
	public String editProduct(Model model, Integer id){
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "editProduct";
	}
	
	@RequestMapping(value="/updateproduct")
	public String updateProduct(@ModelAttribute("product")Product product){
		productService.editProduct(product);
		return "redirect:/listproduct";
	}
	
	@RequestMapping(value="/editmultiproducts")
	public String editMultiProducts(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productList", productService.listProduct());
		return "editMultiProducts";
	}
	
	@RequestMapping(value="/updatemultiproducts")
	public String updateMultiProducts(ProductDTO productDTO) {
		List<Product> productList = productDTO.getMultiProducts();
		for(Product product: productList) {
			productService.editProduct(product);
		}
		return "success";
	}
	
	@RequestMapping(value="/deleteproduct")
	public String deleteProduct(@ModelAttribute("product")Product product) {
		productService.deleteProduct(product);
		return "redirect:/listproduct";
	}
	
	@RequestMapping(value="/deletemultiproducts")
	public String delteMultiProducts(int[] products_id) { //The argument "product_id" is bound to the parameters passed by the checkbox by name
		productService.deleteMultiProducts(products_id);
		return "success";
	}
	
	@RequestMapping(value="/autoinsertproducts")
	public String autoInsertProducts() {
		productService.autoinsertProduct();
		return "redirect:/listproduct";
	}
	
	@RequestMapping(value="/autoinsertrollback")
	public String autoInsertRollback(){
		try{
			productService.autoinsertrbProduct();
		}catch(RuntimeException e){
			logger.error("Exception: "+e.getClass()+" was detected, insert rolling back...");
			return "errorPage";
		}
		return "redirect:/listproduct";
	}

}
