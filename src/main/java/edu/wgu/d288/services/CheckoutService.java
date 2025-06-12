package edu.wgu.d288.services;


import edu.wgu.d288.services.Purchase;

public interface CheckoutService {


    PurchaseResponse placeOrder(Purchase purchase);
}
