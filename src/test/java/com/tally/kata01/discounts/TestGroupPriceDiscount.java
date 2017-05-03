package com.tally.kata01.discounts;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Test the GroupPriceDiscount 
 * @author Tally
 *
 */
public class TestGroupPriceDiscount {
	 
	private static final String DISCOUNT_NAME_1 = "discount-1";
	
	private Discount groupPriceDiscount; 

	@Before
	public void init() {
		/**
		 * The discount is buy 10 for 15 GBP
		 */
		groupPriceDiscount = new GroupPriceDiscount(DISCOUNT_NAME_1, BigDecimal.TEN, BigDecimal.valueOf(15));
	}

	@Test
	/**
	 * Buy 1 unit
	 * The discount is 10 for 15 GBP.
	 * The full price per item is 2GBP
	  
	 * The discount is 0 as there are not enough items
	 */
	public void testSmallPurchaesDiscountValue() {
		BigDecimal discoutnValue =groupPriceDiscount.getDiscountValue(BigDecimal.ONE, BigDecimal.valueOf(25l));
		Assert.assertTrue("Expected discount is zero due to small purchase", discoutnValue.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Test
	/**
	 * Buy 10 units
	 * The discount is 10 for 15 GBP.
	 * The full price per item is 2GBP
	 * The total price without discount is 10*2 GBP
	 * The total price with the discount is 15 GBP
	 * The discount is 10*2 - 15 = 5
	 */
	public void testAccuratePurchaesDiscountValue() {
		BigDecimal discoutnValue =groupPriceDiscount.getDiscountValue(BigDecimal.TEN, BigDecimal.valueOf(2l));
		Assert.assertTrue("Expected discount is 5", discoutnValue.compareTo(BigDecimal.valueOf(5l)) == 0);
	}
	
	@Test
	/**
	 * Buy 30 units
	 * The discount is 10 for 15 GBP.
	 * The full price per item is 2GBP
	 * The total price without discount is 30*2 GBP
	 * The total price with the discount is 3*15 GBP
	 * The discount is 30*2 - 3*15 = 15
	 */
	public void testMultiplePurchaesDiscountValue() {
		BigDecimal discoutnValue =groupPriceDiscount.getDiscountValue(BigDecimal.valueOf(30l), BigDecimal.valueOf(2l));
		Assert.assertTrue("Expected discount is 15", discoutnValue.compareTo(BigDecimal.valueOf(15l)) == 0);
	}
	
	
	@Test
	/**
	 * Buy 14 units
	 * The discount is 10 for 15 GBP.
	 * The full price per item is 2GBP
	 * The total price without discount is 14*2 = 28 GBP
	 * The total price with the discount is 15 + 4*2  = 23 GBP
	 * The discount is 28 - 23 = 5
	 */
	public void testNotAccuratePurchaesDiscountValue() {
		BigDecimal discoutnValue =groupPriceDiscount.getDiscountValue(BigDecimal.valueOf(14l), BigDecimal.valueOf(2l));
		Assert.assertTrue("Expected discount is 5", discoutnValue.compareTo(BigDecimal.valueOf(5l)) == 0);
	}
	
	@Test
	/**
	 * Buy 34 units
	 * The discount is 10 for 15 GBP.
	 * The full price per item is 2GBP
	 * The total price without discount is 34*2 = 68 GBP 
	 * The total price with the discount is 15*3  + 4*2  = 53 GBP
	 * The discount is 68 - 53 = 15
	 */
	public void testMultipleNotAccuratePurchaesDiscountValue() {
		BigDecimal discoutnValue =groupPriceDiscount.getDiscountValue(BigDecimal.valueOf(34l), BigDecimal.valueOf(2l));
		Assert.assertTrue("Expected discount is 15", discoutnValue.compareTo(BigDecimal.valueOf(15l)) == 0);
	}
	
	
	 
	 
}
