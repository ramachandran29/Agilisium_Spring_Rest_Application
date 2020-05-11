package com.agilisium.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agilisium.spring.entity.ProductDetails;
import com.agilisium.spring.request.AdminCredentialsrequest;
import com.agilisium.spring.response.AgilisiumResponse;
import com.agilisium.spring.service.ProductDetailsService;

@RestController
public class ProductDetailsController {
	
	@Autowired
	private ProductDetailsService productDetailsService;
	
	
		
	@PostMapping("/saveProducts") 
	public ResponseEntity<?> saveProducts(@RequestBody ProductDetails productDetails) {
		String productCode = productDetailsService.saveProducts(productDetails);
		return ResponseEntity.status(HttpStatus.OK).body("Your Product has been created and your product code : "+productCode);
		
	}
	
	@GetMapping("/getProductDetails/{ProductCode}")
	public ResponseEntity<ProductDetails> getProductsDetails(@PathVariable("ProductCode") String ProductCode) {
		ProductDetails productDetails = null;
		try {
			productDetails = productDetailsService.getProductsDetails(ProductCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(productDetails);
	}
	
	

}
