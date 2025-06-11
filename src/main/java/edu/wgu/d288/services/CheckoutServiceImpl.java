package edu.wgu.d288.services;

import edu.wgu.d288.dao.CartRepository;
import edu.wgu.d288.dao.CustomerRepository;
import edu.wgu.d288.entities.Cart;
import edu.wgu.d288.entities.CartItem;
import edu.wgu.d288.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override

    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {


        Cart cart = cartRepository.findById(purchase.getCart().getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found!"));

        String orderTrackingNumber = generateTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
        if (cartItems != null) {
            for (CartItem item : cartItems) {
                item.setCart(cart);
            }
            cart.setCartItems(cartItems);


            Customer customer = customerRepository.findById(purchase.getCustomer().getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found!"));

            cart.setCustomer(customer);
            customer.getCarts().add(cart);


            customerRepository.save(customer);
        }

        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateTrackingNumber() {
        return UUID.randomUUID().toString();


    }
}