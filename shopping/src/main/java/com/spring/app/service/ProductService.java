package com.spring.app.service;

import java.util.List;

import com.spring.app.entity.Product;

public interface ProductService {

	void AddProduct(Product p);

	List<Product> listProduct();

}
