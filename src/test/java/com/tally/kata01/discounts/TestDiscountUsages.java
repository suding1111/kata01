package com.tally.kata01.discounts;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Test the discount usages api.
 * This test use a generic discount (TestDiscount) which includes only the discounts' common details
 * 
 * @author Tally
 *
 */
public class TestDiscountUsages {
	 
	private static final String DISCOUNT_NAME_1 = "discount-1";
	
	private Discount testDiscount; 

	@Before
	public void init() {
		/**
		 * The discount is for 10 items
		 */
		testDiscount = new TestDiscount(DISCOUNT_NAME_1, BigDecimal.TEN);
	}

	
	@Test
	/**
	 * Buy 1 unit
	 * The discount is 10 for 10 items
	  
	 * The discount usages is 0 as there are not enough items
	 */
	public void testSmallPurchaesDiscountUsages() {
		long discoutnUsages =testDiscount.getDiscountUsages(BigDecimal.ONE);
		Assert.assertTrue("Expected discount usages is zero due to small purchase", discoutnUsages == 0l);
	}
	
	@Test
	/**
	 * Buy 10 units
	 * The discount is for 10 items
	 * 
	 * The discount usages is 1  
	 */
	public void testAccuratePurchaesDiscountUsages() {
		long discoutnUsages =testDiscount.getDiscountUsages(BigDecimal.TEN);
		Assert.assertTrue("Expected discount usages is 1", discoutnUsages == 1l);
	}
	
	@Test
	/**
	 * Buy 30 units
	 * The discount is for 10 items
	 * 
	 * The discount usages is 3  
	 */
	public void testMultiplePurchaesDiscountUsages() {
		long discoutnUsages =testDiscount.getDiscountUsages(BigDecimal.valueOf(30l));
		Assert.assertTrue("Expected discount usages is 3", discoutnUsages == 3l);
	}
	
	
	 
	@Test
	/**
	 * Buy 44 units
	 * The discount is for 10 items
	 * 
	 * The discount usages is 4  
	 */
	public void testMultipleNotAccuratePurchaesDiscountUsages() {
		long discoutnUsages =testDiscount.getDiscountUsages(BigDecimal.valueOf(44l));
		Assert.assertTrue("Expected discount usages is 4", discoutnUsages == 4l);
	}

	
	/**
	 * A local testing class, focusing on the logic which is common to all discounts
	 * @author Tally
	 *
	 */
	private class TestDiscount extends AbstractDiscount {

		public TestDiscount(String name, BigDecimal numberOfEligibleUnits) {
			super(name, numberOfEligibleUnits);
		}

		@Override
		public BigDecimal getDiscountValue(BigDecimal purchaseUnits, BigDecimal fullPricePerUnit) {
			return null;
		}
		
	}
	 
}
