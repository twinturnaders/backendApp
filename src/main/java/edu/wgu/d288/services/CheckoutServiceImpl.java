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
        Cart cart;
        Long cart_id = purchase.getCart().getCart_id();

        if (cart_id != null) {

            cart = cartRepository.findById(cart_id)
                    .orElseThrow(() -> new RuntimeException("Cart not found: " + cart_id));
        } else {

            cart = new Cart();
        }

        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();
        if (cartItems != null) {
            for (CartItem item : cartItems) {
                item.setCart(cart);
            }
            cart.setCartItems(cartItems);
        }

        Long customer_id = purchase.getCustomer().getCustomer_id();
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customer_id));
        cart.setCustomer(customer);
        customer.getCarts().add(cart);


        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }


}
