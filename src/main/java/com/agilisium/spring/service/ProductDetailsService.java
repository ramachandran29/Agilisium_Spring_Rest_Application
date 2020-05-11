package com.agilisium.spring.service;

import com.agilisium.spring.entity.ProductDetails;

public interface ProductDetailsService {

	String saveProducts(ProductDetails productDetails);

	ProductDetails getProductsDetails(String productCode);


}
