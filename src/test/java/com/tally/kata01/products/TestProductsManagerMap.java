package com.tally.kata01.products;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestProductsManagerMap {
	
	
	private static final String PROD_NAME_1 = "prod-1";
	private static final String PROD_NAME_2 = "prod-2";
	private static final String DISCOUNT_NAME = "discount-1";
	
	private ProductsManager productManager;

	@Before
	public void init() {
		productManager = new ProductsManagerMap();
		productManager.addProduct(new Product(PROD_NAME_1, DISCOUNT_NAME, BigDecimal.valueOf(15))); 
	}

	@Test
	public void testGetExistingProduct() {
		Assert.assertNotNull("Product expected, but not found", productManager.getProduct(PROD_NAME_1));
	}
	
	@Test
	public void testGetUnlistedProduct() {
		
		try {
			productManager.getProduct("unlisted-product-1");
			Assert.assertTrue(false); //Exception is expected as the product is not found
		} catch (Exception e){ 
			Assert.assertTrue(true); 
		}
	}

	@Test
	public void testProductWithoutDiscount() {
			productManager.addProduct(new Product(PROD_NAME_2, null, BigDecimal.TEN));
			Assert.assertNotNull("Product expected, but not found", productManager.getProduct(PROD_NAME_2));
			Assert.assertFalse("Discount name is expected to be null", productManager.getProduct(PROD_NAME_2).getDiscountPlanName().isPresent());
	} 

}
