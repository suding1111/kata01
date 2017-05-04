# Notes

Entities / Objects
==================
The project is composed of 4 entities: 
Basket - a simple list of product name and the quantity. 
Product - the product name, price and link to a discount if there is such
Discount - a discount plan, there are two types of discounts: "Buy 2 for the price x", "Buy 3 and pay only for 2"
Receipt - summery of all the purchaes, the discounts and the totals as demonstrated at the requirments.


Basket
============
Basket - An accumulator which holds the list of purchased products. For each product it includes its name and quantity.
This object is not imutable as the basket is updated each time a product is added to it.


Products
========
Product - representing an available product, including its name, full price and optional discount plan.

In real life the products will be stored in some database or service.
For this implementation I created:
ProductsManager - an interface representing the real products system APIs.
ProductsManagerMap - specific implementation of ProductsManager. This implementation stores the products in a map.


Discounts
=========
Discount - interface, representing all the required APIs of a discount
AbstractDiscount - An abstract class which imlements the base logic which is shared by all the Discount implementations.
GroupPriceDiscount - Specific implementation of discount "Buy 2 for the price x".
QuantityDiscount - Specific implementation of discount "Buy 3 and pay only for 2".

In real life the discounts will be stored in some database or service.
For this implementation I created:
DiscountsManager - an interface representing the real discounts system APIs.
DiscountsManagerMap - specific implementation of DiscountsManager. This implementation stores the discounts in a map.


Receipt
=======
Receipt - This is the main object of this project.
The Receipt provides "generate" method. The input paramter is the basket content.
The outcome of the "generate" are severl objects which include all the required details in order to print the receipt.

The logic of generate is:
Traverse the Basket and find out all the deemed discounts.
Calculate the total full price
Calculate the total discounts








ToDo:
=====
Product should be an interface, and there should be specific implementations for varius products which might


