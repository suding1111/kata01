package com.tally.kata01.products;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * This class represent a product.
 * A product might be connected to a discount plan or not. If not, the discountPlanName is null.
 * 
 * Every product has a full price.
 * 
 * @author Tally
 *
 */
public class Product {
	
	
	private String name;
	private Optional<String> discountPlanName;
	private BigDecimal pricePerUnit;
	
	
	public Product(String name, String discountPlanName, BigDecimal pricePerUnit) {
		this.name = name;
		this.discountPlanName = Optional.ofNullable(discountPlanName);
		this.pricePerUnit = pricePerUnit;
		
	}
	
	public String getName() {
		return name;
	};
	
	public Optional<String> getDiscountPlanName() {
		return discountPlanName;
	}
	
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	
	 
}
