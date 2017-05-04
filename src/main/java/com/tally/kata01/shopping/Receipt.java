package com.tally.kata01.shopping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.stream.Collectors;

import com.tally.kata01.discounts.Discount;
import com.tally.kata01.discounts.management.DiscountsManager;
import com.tally.kata01.products.Product;
import com.tally.kata01.products.ProductsManager;


/**
 * This class represent the customer's receipt.
 * It includes the list of items and the specific price and discount(if any) for this item.
 * 
 *  
 * If the purchased quantity is not an accurate multiply of the discounted quantity, the item will be partially
 * discounted.
 * Example:
 * Item 1 is discounted as "buy 3 and pay for 2". If the customer took 4 units, he/she will pay the discounted amount
 * for 3 items, and full price for 1.
 * 
 * 
 * @author Tally
 *
 */
public class Receipt {
	
	 
	private DiscountsManager discountsManager;
	private ProductsManager productsManager;
	
	private BigDecimal fullPriceCost = BigDecimal.ZERO;
	private BigDecimal totalDiscounts = BigDecimal.ZERO;
 	
	private Set<SelectedDiscount> discounts;
	
	/**
	 * The Reciept constructor
	 * @param discountsManager - The discounts manager
	 * @param productsManager - The products manager
	 */
	public Receipt(DiscountsManager discountsManager, ProductsManager productsManager) {
		super();
		this.discountsManager = discountsManager;
		this.productsManager = productsManager;
	}
	
	
	/**
	 * Build the receipt based on the purchases list
	 * This method build the receiptItems set and prepare all the required calculations in order to print the
	 * receipt
	 * 
	 * @param basket
	 */
	public void generate(Basket basket) {
		
		//Find all the discounts
		discounts = basket.getPurchasesList().keySet().stream()
		.filter(key -> productsManager.getProduct(key).getDiscountPlanName().isPresent() )
		.map(key -> getDiscountDetails(key, basket.getPurchasesList().get(key)))
		.collect(Collectors.toSet());
		
		//The total cost before discount
		fullPriceCost = basket.getPurchasesList().entrySet().stream()
		.map(entry ->  calculateFullPrice(entry.getKey(), entry.getValue()))
		.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		//The total discounts
		totalDiscounts = discounts.stream()
				.map(discountDetails -> discountDetails.getDiscountValue())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private BigDecimal calculateFullPrice(String productName, BigDecimal purchasedQuantity){
		Product product = productsManager.getProduct(productName);
		return product.getPricePerUnit().multiply(purchasedQuantity).setScale(2, RoundingMode.HALF_UP);
		
	}
	
	private SelectedDiscount getDiscountDetails(String productName, BigDecimal purchasedQuantity){
		 
		Product product = productsManager.getProduct(productName);
		Discount discount = discountsManager.getDiscount(product.getDiscountPlanName().get());
		return new SelectedDiscount(discount, purchasedQuantity, product.getPricePerUnit(), product.getName() );
	}
	
	public BigDecimal getFullPriceCost() {
		return fullPriceCost;
	}
	
	public BigDecimal getTotalDiscounts() {
		return totalDiscounts;
	}
	
	public Set<SelectedDiscount> getDiscountDetails() {
		return discounts;
	}

	
}
