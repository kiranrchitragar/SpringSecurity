package com.kiran.springcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiran.springcloud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
