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
        Long cartId = purchase.getCart().getCartId();

        if (cartId != null) {

            cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new RuntimeException("Cart not found: " + cartId));
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

        Long custId = purchase.getCustomer().getCustomerId();
        Customer customer = customerRepository.findById(custId)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + custId));
        cart.setCustomer(customer);
        customer.getCarts().add(cart);


        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }


}
