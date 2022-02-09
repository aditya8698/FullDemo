package com.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spring.app.entity.Product;
import com.spring.app.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/")
	public String addProduct(Model m) {
		m.addAttribute("list", service.listProduct());
		return "index";
	}
	
	@GetMapping("/saveProduct")
	public String insertProduct(@ModelAttribute Product product) {
		service.AddProduct(product);
		return "redirect:/";
	}
	
	
}
