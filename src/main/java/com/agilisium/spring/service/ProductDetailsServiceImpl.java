package com.agilisium.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilisium.spring.dao.ProductDetailsDao;
import com.agilisium.spring.entity.ProductDetails;

@Service
@Transactional
public class ProductDetailsServiceImpl implements ProductDetailsService {
	

	@Autowired
	private ProductDetailsDao productDetailsDao;
	
	@Transactional
	public String saveProducts(ProductDetails productDetails) {
		// TODO Auto-generated method stub
		return productDetailsDao.saveProducts(productDetails);
	}


	public ProductDetails getProductsDetails(String productCode) {
		// TODO Auto-generated method stub
		return productDetailsDao.getProductsDetails(productCode);
	}

}
