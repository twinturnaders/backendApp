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

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository,
                               CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //check for existing cart: else new
        Cart incoming = purchase.getCart();
        Cart cart = (incoming != null
                && incoming.getCartId() != null
                && incoming.getCartId() != 0L)
                ? cartRepository.findById(incoming.getCartId())
                .orElseGet(Cart::new)
                : new Cart();

        //create tracking string
        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // attach items to cart
        Set<CartItem> items = purchase.getCartItems();
        if (items != null && !items.isEmpty()) {
            items.forEach(item -> item.setCart(cart));
            cart.setCartItems(items);
        }

        // check for existing customer :else save new
        Customer incomingCust = purchase.getCustomer();
        Customer customer = customerRepository
                .findById(incomingCust.getCustomerId())
                .orElseGet(() -> customerRepository.save(incomingCust));

        //add customer to cart and cart to customer
        cart.setCustomer(customer);
        customer.getCarts().add(cart);

        //persist car
        cartRepository.save(cart);

        //return order number
        return new PurchaseResponse(orderTrackingNumber);
    }
}
