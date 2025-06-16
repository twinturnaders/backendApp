package edu.wgu.d288.services;

import edu.wgu.d288.dao.CartRepository;
import edu.wgu.d288.dao.CustomerRepository;
import edu.wgu.d288.entities.Cart;
import edu.wgu.d288.entities.CartItem;
import edu.wgu.d288.entities.Customer;
import edu.wgu.d288.entities.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
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
        Date date = new Date();
        cart.setLast_update(date);
        cart.setCreate_date(date);



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
        Customer customer = customerRepository.findById(incomingCust.getCustomerId())
                .orElseGet(() -> customerRepository.save(incomingCust));



        customer.getCarts().add(cart);
        cart.setCustomer(customer);



        cart.setOrderTrackingNumber(orderTrackingNumber);

        //see if any values are read
        Integer party_sizeIn  = purchase.getCart().getParty_size();
        BigDecimal package_priceIn = purchase.getCart().getPackage_price();
        Status statusIn = purchase.getCart().getStatus();

        //set default values
        cart.setParty_size(party_sizeIn != null ? party_sizeIn : 1);
        cart.setPackage_price(package_priceIn != null ? package_priceIn : BigDecimal.ZERO);
        cart.setStatus(statusIn != null ? statusIn : Status.pending);








        //persist car
        cartRepository.save(cart);

        //return order number
        return new PurchaseResponse(

                cart.getOrderTrackingNumber(),
                cart.getId(),
                cart.getPackage_price(),
                cart.getParty_size(),
                cart.getStatus(),
                cart.getCreate_date(),
                cart.getLast_update());

    }
}
