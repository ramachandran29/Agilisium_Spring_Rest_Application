package com.agilisium.spring.dao;

import java.util.List;

import javax.persistence.criteria.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agilisium.spring.entity.ProductDetails;

@Repository
public class ProductDetailsDaoImpl  implements ProductDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	

	public String saveProducts(ProductDetails productDetails) {
		
		getSession().saveOrUpdate(productDetails);
		System.out.println("productDetails.getProductId()========"+productDetails.getProductCode());
		return productDetails.getProductCode();
	}


	public ProductDetails getProductsDetails(String productCode) {
		ProductDetails productDetails = null;
		try {
//			Criteria criteria = getSession().createCriteria(ProductDetails.class);
//	        criteria.add(Restrictions.eq("productCode", productCode));
//	        productDetails = (ProductDetails) criteria.uniqueResult();
	       
			
			
			
			CriteriaBuilder builder = getSession().getCriteriaBuilder();
	        CriteriaQuery<ProductDetails> criteriaQuery = builder.createQuery(ProductDetails.class);
	        Root<ProductDetails> root = criteriaQuery.from(ProductDetails.class);
	        criteriaQuery.select(root).where(builder.equal(root.get("productCode"), productCode));
	        Query<ProductDetails> query = getSession().createQuery(criteriaQuery);
	        productDetails = query.getSingleResult();
	        
	        System.out.println("Products Code : "+productDetails.getProductCode() + " Products Name :  "+productDetails.getProductName());
	        
		}catch (Exception e) {
			e.printStackTrace();
		}
		

		return productDetails;
	}

	
}
