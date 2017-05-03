package com.tally.kata01.discounts;

import java.math.BigDecimal;

/**
 * This type of discount represent the "buy 3 and pay only for 2"
 * 
 * @author Tally
 *
 */
public class QuantityDiscount extends AbstractDiscount   {
	
	
	/**
	 * The number of units to pay for
	 */
	private BigDecimal numberOfPricedUnits;

	/**
	 * The Quantity Discount constructor
	 *  
	 * @param name - the discount name
	 * @param numberOfEligibleUnits - the number of units to purchase in order to get the discount
	 * @param discountedPricePerGroup - the number of items to pay for
	 */ 
	public QuantityDiscount(String name, BigDecimal numberOfEligibleUnits, BigDecimal numberOfPricedUnits) {
		super(name, numberOfEligibleUnits);
		this.numberOfPricedUnits = numberOfPricedUnits;
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
		
		BigDecimal discountedGroupCost =  groupsAndRemainder[DISCOUNTABLE_GROUPS].multiply(fullPricePerUnit).multiply(numberOfPricedUnits);
		
		BigDecimal extraUnitsCost = groupsAndRemainder[FULL_PRICE_UNITS].multiply(fullPricePerUnit);
		
		BigDecimal fullPrice = purchaseUnits.multiply(fullPricePerUnit);
		
		return fullPrice.add(discountedGroupCost.negate()).add(extraUnitsCost.negate());
	} 


}
