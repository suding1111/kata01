package com.tally.kata01.discounts;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Test the QuantityDiscount
 * 
 * @author Tally
 *
 */
public class TestQuantityDiscount {
	 
	private static final String DISCOUNT_NAME_1 = "discount-1";
	private static final String DISCOUNT_NAME_2 = "discount-2";
	
	private Discount quantityDiscount; 

	@Before
	public void init() {
		quantityDiscount = new QuantityDiscount(DISCOUNT_NAME_1, BigDecimal.TEN, BigDecimal.ONE);
	}

	@Test
	/**
	 * Buy one unit
	 * The discount is 10 for 1, so discount is zero
	 */
	public void testSmallPurchaesDiscountValue() {
		BigDecimal discoutnValue =quantityDiscount.getDiscountValue(BigDecimal.ONE, BigDecimal.valueOf(20l));
		Assert.assertTrue("Expected discount is zero due to small purchase", discoutnValue.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Test
	/**
	 * Buy 10 units
	 * The discount is 10 for 1.
	 * The full price per item is 2GBP
	 * The total price without discount is 10*2 GBP
	 * The total price with the discount is 1*2 GBP
	 * The discount is 10*2 - 1*2 = 18
	 */
	public void testAccuratePurchaesDiscountValue() {
		BigDecimal discoutnValue =quantityDiscount.getDiscountValue(BigDecimal.TEN, BigDecimal.valueOf(2l));
		Assert.assertTrue("Expected discount is 18", discoutnValue.compareTo(BigDecimal.valueOf(18l)) == 0);
	}
	
	@Test
	/**
	 * Buy 30 units
	 * The discount is 10 for 1.
	 * The full price per item is 2GBP
	 * The total price without discount is 30*2 GBP
	 * The total price with the discount is 3*2 GBP
	 * The discount is 30*2 - 3*1*2 = 54
	 */
	public void testMultiplePurchaesDiscountValue() {
		BigDecimal discoutnValue =quantityDiscount.getDiscountValue(BigDecimal.valueOf(30l), BigDecimal.valueOf(2l));
		Assert.assertTrue("Expected discount is 54", discoutnValue.compareTo(BigDecimal.valueOf(54l)) == 0);
	}
	
	
	@Test
	/**
	 * Buy 15 units
	 * The discount is 10 for 1.
	 * The full price per item is 2GBP
	 * The total price without discount is 15*2 GBP
	 * The total price with the discount is 1*2 GBP + 5*2 GBP
	 * The discount is 15*2 - (1*2 GBP + 5*2 GBP) = 18
	 */
	public void testNotAccuratePurchaesDiscountValue() {
		BigDecimal discoutnValue =quantityDiscount.getDiscountValue(BigDecimal.valueOf(15l), BigDecimal.valueOf(2l));
		Assert.assertTrue("Expected discount is 18", discoutnValue.compareTo(BigDecimal.valueOf(18l)) == 0);
	}
	
	@Test
	/**
	 * Buy 34 units
	 * The discount is 5 for 2.
	 * The full price per item is 3GBP
	 * The total price without discount is 34*3 = 102 GBP 
	 * The total price with the discount is 6*2*3  + 4*3  = 48 GBP
	 * The discount is 34*3 - (6*2*3  + 4*3) = 54
	 */
	public void testMultipleNotAccuratePurchaesDiscountValue() {
		Discount discount5For2 = new QuantityDiscount(DISCOUNT_NAME_2, BigDecimal.valueOf(5l), BigDecimal.valueOf(2l));
		
		BigDecimal discoutnValue =discount5For2.getDiscountValue(BigDecimal.valueOf(34l), BigDecimal.valueOf(3l));
		Assert.assertTrue("Expected discount is 54", discoutnValue.compareTo(BigDecimal.valueOf(54l)) == 0);
	}
	

	 
}
