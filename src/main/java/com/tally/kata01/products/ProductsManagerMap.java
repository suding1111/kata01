package com.tally.kata01.products;

import java.util.HashMap;
import java.util.Optional;
 
/**
 * This implementation of the products' repository is for the exercise only.
 * It does not persist the products, so it is not really usable in real life.
 * 
 * Also, a real implementation should handle the product updates and concurrency aspects of the update
 * 
 * 
 * @author Tally
 *
 */
public class ProductsManagerMap implements ProductsManager {

	private HashMap<String, Product> products = new HashMap<String, Product>();
	
	
	@Override
	public Product getProduct(String productName) {
		return Optional.ofNullable(products.get(productName)).get();
	}
	
	
	@Override
	public void addProduct(Product product){
		products.put(product.getName(), product);
	}


}
