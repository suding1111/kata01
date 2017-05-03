package com.tally.kata01.discounts.management;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tally.kata01.discounts.QuantityDiscount;
import com.tally.kata01.discounts.management.DiscountsManager;
import com.tally.kata01.discounts.management.DiscountsManagerMap;
/**
 * Test the DiscountManager map based implementation
 * 
 * @author Tally
 *
 */
public class TestDiscountsManagerMap {
	
	
	 
	private static final String DISCOUNT_NAME_1 = "discount-1";
	 
	
	private DiscountsManager discountsManager;

	@Before
	public void init() {
		discountsManager = new DiscountsManagerMap();
		discountsManager.addDiscount(new QuantityDiscount(DISCOUNT_NAME_1, BigDecimal.TEN, BigDecimal.ONE));
	}

	@Test
	public void testGetExistingDiscount() {
		Assert.assertNotNull("Discoutn expected, but not found", discountsManager.getDiscount(DISCOUNT_NAME_1));
	}
	
	@Test
	public void testGetUnlistedDiscount() {
		
		try {
			discountsManager.getDiscount("unlisted-discoutn-1");
			Assert.assertTrue(false); //Exception is expected as the product is not found
		} catch (Exception e){ 
			Assert.assertTrue(true); 
		}
	}

	 
}
