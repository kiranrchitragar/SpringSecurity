package com.kiran.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kiran.springcloud.dto.Coupon;
import com.kiran.springcloud.model.Product;
import com.kiran.springcloud.repository.ProductRepository;

@RestController
@RequestMapping("/productApi")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public Product create(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject("http://localhost:8080/couponApi/coupons/"+product.getCouponCode(), Coupon.class);
		
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepository.save(product);
	}
	
	
	
	
//	@RequestMapping(value = "/products/{code}", method = RequestMethod.GET)
//	public Product getProduct(@PathVariable("code") String code) {
//		return productRepository.findByCode(code);
//	}

}
