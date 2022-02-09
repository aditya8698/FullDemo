package com.spring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.entity.Product;
import com.spring.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired 
	private ProductRepository prepo;
	
	@Override
	public void AddProduct(Product p) {
		prepo.save(p);
	}

	@Override
	public List<Product> listProduct() {
		List<Product> plist = prepo.findAll();
		return plist;
	}

}
