package edu.wgu.d288.services;

import edu.wgu.d288.dao.CartRepository;
import edu.wgu.d288.dao.CustomerRepository;
import edu.wgu.d288.entities.Cart;
import edu.wgu.d288.entities.CartItem;
import edu.wgu.d288.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Cart cart = purchase.getCart();

        String order_tracking_number = generateTrackingNumber();
        cart.setOrderTrackingNumber(order_tracking_number);

        Set<CartItem> CartItemSet = purchase.getCartItems();
        for (CartItem item : CartItemSet) {
            item.setCart(cart);
        }
        cart.setCartItems(CartItemSet);

        Customer customer = purchase.getCustomer();
        cart.setCustomer(customer);

        customer.getCarts().add(cart);

        customerRepository.save(customer);

        return new PurchaseResponse(order_tracking_number);
    }

    private String generateTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
