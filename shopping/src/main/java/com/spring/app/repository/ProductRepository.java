package com.spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
}
