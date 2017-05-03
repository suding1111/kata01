package com.tally.kata01.discounts.management;

 

import com.tally.kata01.discounts.Discount;

public interface DiscountsManager {

	/**
	 * Fetch discount details.
	 * 
	 * @param discountName - the discount to fetch 
	 * @return the Discount specific instance
	 * @throws NoSuchElementException - if there is no such discount
	 */
	public Discount getDiscount(String discountName);

	/**
	 * Add a new discount to the repository
	 * @param discount
	 */
	public void addDiscount(Discount discount);

}