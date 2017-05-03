package com.tally.kata01.discounts;

import java.math.BigDecimal;

/**
 * This type of discount represent the "buy 2 for the price x"
 * 
 * @author Tally
 *
 */
public class GroupPriceDiscount extends AbstractDiscount  {
	
	/**
	 * The price to pay for each group of eligible units
	 */
	private BigDecimal discountedPricePerGroup;
	
	/**
	 * The Group Price Discount constructor
	 *  
	 * @param name - the discount name
	 * @param numberOfEligibleUnits - the number of units to purchase in order to get the discount
	 * @param discountedPricePerGroup - the price for every group of discounted items
	 */
	public GroupPriceDiscount(String name, BigDecimal numberOfEligibleUnits, BigDecimal discountedPricePerGroup) {
		super(name, numberOfEligibleUnits);
		this.discountedPricePerGroup = discountedPricePerGroup;
	}
	
	/**
	 * Return the discount value
	 * 
	 * @param purchaseUnits - the number of units to be purchased 
	 * @param fullPricePerUnit - the full price per unit
	 * @return
	 */
	@Override
	public BigDecimal getDiscountValue(BigDecimal purchaseUnits, BigDecimal fullPricePerUnit) {
		if(purchaseUnits.compareTo(getNumberOfEligibleUnits()) < 0)
			return BigDecimal.ZERO;
		
		BigDecimal[] groupsAndRemainder = purchaseUnits.divideAndRemainder(getNumberOfEligibleUnits());
		
		BigDecimal discountedGroupCost = groupsAndRemainder[DISCOUNTABLE_GROUPS].multiply(discountedPricePerGroup);
		
		BigDecimal extraUnitsCost = groupsAndRemainder[FULL_PRICE_UNITS].multiply(fullPricePerUnit);
		
		BigDecimal fullPrice = purchaseUnits.multiply(fullPricePerUnit);
		
		return fullPrice.add(discountedGroupCost.negate()).add(extraUnitsCost.negate());
	} 

	
}
