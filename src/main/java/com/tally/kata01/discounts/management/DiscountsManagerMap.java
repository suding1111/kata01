package com.tally.kata01.discounts.management;

import java.util.HashMap;
import java.util.Optional;

import com.tally.kata01.discounts.Discount;

/**
 * This implementation of the discounts' repository is for the exercise only.
 * It does not persist the discounts, so it is not really usable in real life.
 * 
 * Also, a real implementation should handle the discounts updates and concurrency aspects of the update
 * 
 * 
 * @author Tally
 *
 */
public class DiscountsManagerMap implements DiscountsManager {

	private HashMap<String, Discount> discounts = new HashMap<String, Discount>();
	
	 
	@Override
	public Discount getDiscount(String discountName) {
		return Optional.ofNullable(discounts.get(discountName)).get();
	}
	
	 
	 
	@Override
	public void addDiscount(Discount discount){
		discounts.put(discount.getName(), discount);
	}
}
