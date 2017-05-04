package com.tally.kata01.shopping;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tally.kata01.discounts.Discount;
import com.tally.kata01.discounts.GroupPriceDiscount;
import com.tally.kata01.discounts.QuantityDiscount;
import com.tally.kata01.discounts.management.DiscountsManager;
import com.tally.kata01.discounts.management.DiscountsManagerMap;
import com.tally.kata01.products.Product;
import com.tally.kata01.products.ProductsManager;
import com.tally.kata01.products.ProductsManagerMap;
import com.tally.kata01.shopping.Basket;
import com.tally.kata01.shopping.Receipt;
/**
 * 
 * Test the Receipt and demonstrate the requested example 
 * 
 * 
 * @author Tally
 *
 */
public class TestReceipt {
	
	private static final String PRODUCT_COKE = "Coke";
	private static final String PRODUCT_BEANS = "Beans";
	private static final String PRODUCT_ORANGES = "Oranges";
	
	private static final String DISCOUNT_2_FOR_1_GBP = "2 for 1 GBP";
	private static final String DISCOUNT_3_FOR_2 = "3 for 2";
	
	private DiscountsManager discountsManager;
	private ProductsManager productsManager; 
	private Basket basket;

	@Before
	/**
	 * Init the products, discounts the the basket
	 */
	public void initPurchaesList() {
		basket = buildBasket();
		discountsManager = buildDiscountsManager();
		productsManager = buildProductsManager();
	}
	
	
	@Test
	/**
	 * Test the basket as demonstrated in the example
	 */
	public void testExamplePurchaesList() {
		
		Receipt receipt = new Receipt(discountsManager, productsManager);

		receipt.generate(basket);
		
		BigDecimal subToatl = receipt.getFullPriceCost();
		Assert.assertTrue("Total price cost should be 3.30", new BigDecimal("3.30").compareTo(subToatl) == 0);
		
		BigDecimal discountsToatal = receipt.getTotalDiscounts();
		Assert.assertTrue("Total discount should be 0.90", new BigDecimal("0.90").compareTo(discountsToatal) == 0);
		
		Set<SelectedDiscount> selectedDiscounts = receipt.getDiscountDetails();
		Assert.assertNotNull("Discounts list cannot be null", selectedDiscounts);
		Assert.assertTrue("Number of selected discounts should be 2", selectedDiscounts.size()  == 2);
		
		selectedDiscounts.stream().forEach(discount -> verifyDiscount(discount));
		
 	}
	
	
	private void verifyDiscount(SelectedDiscount discount) {
		switch(discount.getProductName()) {
		case PRODUCT_COKE:
			Assert.assertEquals("Unexpected discount name "+discount.getName(),discount.getName(),DISCOUNT_2_FOR_1_GBP);
			Assert.assertTrue("Coke discount value should be 0.40", new BigDecimal("0.40").compareTo(discount.getDiscountValue()) == 0);
			Assert.assertEquals("Coke discount should be used once", 1l, discount.getDiscountUsages());
			break;
		case PRODUCT_BEANS:
			Assert.assertEquals("Unexpected discount name "+discount.getName(),discount.getName(),DISCOUNT_3_FOR_2);
			Assert.assertTrue("Beans discount value should be 0.50", new BigDecimal("0.50").compareTo(discount.getDiscountValue()) == 0);
			Assert.assertEquals("Beans discount should be used once", 1l, discount.getDiscountUsages());
			break;
		default:
			Assert.assertTrue("Unexpected product name "+discount.getProductName(),false);
		}
		 
	}




	@Test
	/**
	 * Test the case of empty basket
	 */
	public void testEmptyPurchaesList() {
		
		Receipt receipt = new Receipt(discountsManager, productsManager);
		Basket emptyBasket = new Basket();
		
		receipt.generate(emptyBasket);
		
		BigDecimal subToatl = receipt.getFullPriceCost();
		Assert.assertTrue("Total price cost should be zero", BigDecimal.ZERO.compareTo(subToatl) == 0);
		
		BigDecimal discountsToatal = receipt.getTotalDiscounts();
		Assert.assertTrue("Total discount should be zero", BigDecimal.ZERO.compareTo(discountsToatal) == 0);
 	}
	
	/**
	 * Initiate the DiscountsManager with the example discounts
	 */
	private DiscountsManager buildDiscountsManager() {
		Discount discount;
		DiscountsManager discountsManager = new DiscountsManagerMap();
		
		discount = new QuantityDiscount(DISCOUNT_3_FOR_2, BigDecimal.valueOf(3l), BigDecimal.valueOf(2l));
		discountsManager.addDiscount(discount);		
		
		discount = new GroupPriceDiscount(DISCOUNT_2_FOR_1_GBP, BigDecimal.valueOf(2l), BigDecimal.valueOf(1l));
		discountsManager.addDiscount(discount);	
		
		return discountsManager;
	}
	
	/**
	 * Initiate the ProductsManager with the example products
	 */
	private ProductsManager buildProductsManager() {
		Product product;
		ProductsManager productsManager = new ProductsManagerMap();
		
		product = new Product(PRODUCT_BEANS, DISCOUNT_3_FOR_2, new BigDecimal("0.50"));
		productsManager.addProduct(product);		
		
		product = new Product(PRODUCT_COKE, DISCOUNT_2_FOR_1_GBP, new BigDecimal("0.70"));
		productsManager.addProduct(product);
		
		product = new Product(PRODUCT_ORANGES, null, new BigDecimal("1.99"));
		productsManager.addProduct(product);	
		
		return productsManager;
	}
	
	/**
	 * Initiate the basket 
	 */
	private Basket buildBasket() {
		 
		Basket basket = new Basket();
		//It is possible to add each item unit separately
		basket.addItem(PRODUCT_BEANS, BigDecimal.valueOf(1l));
		basket.addItem(PRODUCT_BEANS, BigDecimal.valueOf(1l));
		basket.addItem(PRODUCT_BEANS, BigDecimal.valueOf(1l));
		
		//It is possible to add a number of units for this item
		basket.addItem(PRODUCT_COKE, BigDecimal.valueOf(2l));
		basket.addItem(PRODUCT_ORANGES, new BigDecimal("0.200"));
		 
		return basket;
	}
	

	
}
