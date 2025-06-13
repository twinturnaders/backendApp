package edu.wgu.d288.services;


import edu.wgu.d288.services.Purchase;
import org.springframework.beans.factory.annotation.Autowired;

public interface CheckoutService {


    PurchaseResponse placeOrder(Purchase purchase);


}
