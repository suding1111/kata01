package com.tally.kata01.products;

public interface ProductsManager {

	/**
	 * Fetch product details.
	 * 
	 * @param productName - the product to fetch
	 * @return the Product specific instance
	 * @throws NoSuchElementException - if there is no such product
	 */
	public Product getProduct(String productName);

	/**
	 * Add a new product to the repository
	 * @param product
	 */
	public void addProduct(Product product);

}