package com.agilisium.spring.dao;

import com.agilisium.spring.entity.ProductDetails;


public interface ProductDetailsDao {

	String saveProducts(ProductDetails productDetails);

	ProductDetails getProductsDetails(String productCode);


	
}
