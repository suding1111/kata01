package com.tally.kata01.discounts;

import java.math.BigDecimal;
/**
 * The discount interface
 * 
 * @author Tally
 *
 */
public interface Discount {
	
	public final static int DISCOUNTABLE_GROUPS = 0 ;
	public final static int FULL_PRICE_UNITS  = 1 ;
	 
	
	/**
	 * Returns the discount name
	 * @return
	 */
	public String getName();

	/**
	 * Returns the number of time the discount was applied during this purchase
	 * @param purchasedQuantity - the number of units of the product
	 * @return the number of time the discount was applied during this purchase
	 */
	public long getDiscountUsages(BigDecimal purchasedQuantity);
	
	/**
	 * Return the discount value - how much should be deducted from the full price
	 * 
	 * @param purchaseUnits - the number of units to be purchased 
	 * @param fullPricePerUnit - the full price per unit
	 * @return
	 */
	public BigDecimal getDiscountValue(BigDecimal purchaseUnits, BigDecimal fullPricePerUnit);
}