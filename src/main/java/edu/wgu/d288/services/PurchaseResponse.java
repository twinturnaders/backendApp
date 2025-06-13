package edu.wgu.d288.services;


import edu.wgu.d288.dao.CartRepository;
import edu.wgu.d288.entities.Cart;
import edu.wgu.d288.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {
    @Autowired
    CartRepository cartRepository;

    private Cart cart;
    private String orderTrackingNumber;


    private Integer party_size;
    private BigDecimal package_price;
    private Status status;
    private Date last_update;
    private Date created_date;

    public PurchaseResponse(String orderTrackingNumber) {
    }
}
