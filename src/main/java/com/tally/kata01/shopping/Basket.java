package com.tally.kata01.shopping;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**
 * This class accumulate the list of purchased items.
 * It has one entry per item and the total purchased quantity  
 * 
 * @author Tally
 *
 */
public class Basket {
	private HashMap<String, BigDecimal> items;
	
/**
 * Create a new purchases list with no items
 */
	public Basket() {
		items = new HashMap<String, BigDecimal>();
	}

	/**
	 * Add an item to the purchases list
	 * 
	 * If the item already listed, add the additional quantity to the already listed item.
	 * 
	 * @param name - The Item name
	 * @param quantity - The quantity of this item purchase. It might be its weight or number of items
	 */
	public void addItem(String name, BigDecimal quantity) {
		    BigDecimal listedQuantity = Optional.ofNullable(items.get(name)).orElse(BigDecimal.ZERO);
		    BigDecimal updatedQuantity = listedQuantity.add(quantity);
		    items.put(name, updatedQuantity);
	}
	
	/**
	 * Get the current purchases list
	 * @return an UNMODIFIABLE purchases list
	 */
	public Map<String, BigDecimal> getPurchasesList() {
		return (Map<String, BigDecimal>) Collections.unmodifiableMap(items);
	}
	
}
