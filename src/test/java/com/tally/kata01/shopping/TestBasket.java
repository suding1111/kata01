package com.tally.kata01.shopping;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tally.kata01.shopping.Basket;

public class TestBasket {
	
	private static final String ITEM_1 = "Item-1";
	private static final String ITEM_2 = "Item-2";
	
	private Basket purchasesList;

	@Before
	public void initPurchaesList() {
		purchasesList = new Basket();
	}

	@Test
	public void testPurchaesListBeforeAnyItemAdded() {
		Assert.assertTrue("Initial purchases list should be empty", purchasesList.getPurchasesList().isEmpty());
	}

	@Test
	public void testAddingTheFirstItem() {
		purchasesList.addItem(ITEM_1, BigDecimal.TEN);
		Assert.assertEquals("Purchases list should include one item", 1, 
				purchasesList.getPurchasesList().size());
	}

	@Test
	public void testAddingSeveralItems() {
		purchasesList.addItem(ITEM_1, BigDecimal.TEN);
		purchasesList.addItem(ITEM_2, BigDecimal.ONE);

		Assert.assertEquals("Purchases list should include 2 items", 2, 
				purchasesList.getPurchasesList().size());

		Assert.assertEquals("Item-1 quantity should be 10", 0,
				BigDecimal.TEN.compareTo(purchasesList.getPurchasesList().get(ITEM_1)));

		Assert.assertEquals("Item-2 quantity should be 1", 0,
				BigDecimal.ONE.compareTo(purchasesList.getPurchasesList().get(ITEM_2)));
	}

	@Test
	public void testAddingAlreadyListedItem() {
		purchasesList.addItem(ITEM_1, BigDecimal.TEN);
		purchasesList.addItem(ITEM_1, BigDecimal.ONE);

		BigDecimal expectedQuantity = new BigDecimal(11);
		Assert.assertEquals("Item-1 quantity should be 11", 0,
				expectedQuantity.compareTo(purchasesList.getPurchasesList().get(ITEM_1)));

		Assert.assertEquals("Purchases list should include one item", 1, 
				purchasesList.getPurchasesList().size());
	}

}
