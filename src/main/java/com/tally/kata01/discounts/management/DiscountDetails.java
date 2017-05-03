package com.tally.kata01.discounts.management;

import java.math.BigDecimal;

import com.tally.kata01.discounts.Discount;

/**
 * This class include all the details about the discounts which were used during the purchase
 * @author Tally
 *
 */

public class DiscountDetails {
	 
		//The discount name
		private String name;
		
		//The number of times the discount was applied for this product in this purchase
		//For example: in 'buy 3 for 10GBP', on purchase of 6 items the discount will be applied twice.
		//             in 'buy 4 for 2', on purchase of 6 items the discount will be applied once.
		private long discountUsages = 0;
		
		//The discount value - the amount of money to be deducted due to the applied discount
		BigDecimal discountValue = BigDecimal.ZERO;
		
		
		public DiscountDetails(Discount discount, BigDecimal purchasedQuantity, BigDecimal fullPricePerUnit){
			this.name = discount.getName(); 
			this.discountUsages = discount.getDiscountUsages(purchasedQuantity);
			this.discountValue = discount.getDiscountValue(purchasedQuantity, fullPricePerUnit);
		}


		public String getName() {
			return name;
		}

		public long getDiscountUsages() {
			return discountUsages;
		}

		public BigDecimal getDiscountValue() {
			return discountValue;
		}

	}

