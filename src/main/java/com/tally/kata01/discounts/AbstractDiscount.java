package com.tally.kata01.discounts;

import java.math.BigDecimal;

/**
 * Abstract class which implements the common logic for all available discounts
 * @author Tally
 *
 */
public abstract class AbstractDiscount implements Discount  {
	
	
	
	/**
	 * The discount name
	 */
	private String name;
	
	/**
	 * The number of units which should be purchased to get the discount
	 */
	private BigDecimal numberOfEligibleUnits;
 	 
	
	public AbstractDiscount(String name, BigDecimal numberOfEligibleUnits) {
		super();
		this.name = name;
		this.numberOfEligibleUnits = numberOfEligibleUnits;
	}
	
	
	@Override
	/**
	 * Returns the number of time the discount was applied during this purchase
	 * @param purchasedQuantity - the number of units of the product
	 * @return the number of time the discount was applied during this purchase
	 */
	public long getDiscountUsages(BigDecimal purchasedQuantity) {
		if(purchasedQuantity.compareTo(numberOfEligibleUnits) < 0)
			return 0l;
		
		return purchasedQuantity.divideToIntegralValue(numberOfEligibleUnits).longValue();
	}
 	 

	@Override
	public String getName() {
		return name;
	}


	protected BigDecimal getNumberOfEligibleUnits() {
		return numberOfEligibleUnits;
	}

	
}
