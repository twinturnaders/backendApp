package edu.wgu.d288.services;


import edu.wgu.d288.services.Purchase;

public interface CheckoutService {

    private PurchaseResponse placeOrder() {
        return placeOrder(null);
    }

    PurchaseResponse placeOrder(Purchase purchase);
}
