# Notes
Technical Details
=================
Java 8, JUnit
Git URL: https://github.com/suding1111/kata01

In order to build the project please use "mvn clean install" from the git command line window.

In order to view the results, please activate com.tally.kata01.shopping.TestReceipt. It is a JUnit test.



Entities / Objects
==================
The project is composed of 3 packages:
shopping
product
discount
 
 
Shopping
============
Basket:  
An accumulator which holds the list of purchased products. For each product it includes its name and quantity.
This object is not immutable as the basket is updated each time a product is added to it.

SelectedDiscount:
A specific discount which was activated during this purchase. It include the details about the discount name, value, the number
of times it was used for this product, product name.
(If the discount is "buy 2 for 10$" and the basket include 4 items of this product, the discount was used twice.)
 
Receipt:
This is the main object of this project.
The Receipt provides "generate" method. The input parameter is the basket content.
The outcome of the "generate" are several objects which include all the required details in order to print the receipt.
The logic of "generate" is:
Traverse the Basket and find out all the used discounts - This is a set of SelectedDiscount instances
Calculate the total full price
Calculate the total discounts


Products
========
Product:
Representing an available product, including its name, full price and optional discount plan.

In real life the products will be stored in some database or service.
For this implementation I created:

ProductsManager:
An interface representing the real products system APIs.

ProductsManagerMap:
Specific implementation of ProductsManager. This implementation stores the products in a map.


Discounts
=========
Discount:
Interface, representing all the required APIs of a discount

AbstractDiscount:
An abstract class which implements the base logic which is shared by all the Discount implementations.

GroupPriceDiscount:
Specific implementation of discount "Buy 2 for the price x".

QuantityDiscount:
Specific implementation of discount "Buy 3 and pay only for 2".

In real life the discounts will be stored in some database or service.
For this implementation I created:

DiscountsManager:
An interface representing the real discounts system APIs.

DiscountsManagerMap:
Specific implementation of DiscountsManager. This implementation stores the discounts in a map.




ToDo:
=====
Product should be an interface, and there should be specific implementations for various products which might


