package edu.wgu.d288.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.transaction.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    private Long id;


    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    private BigDecimal package_price;

    private Integer party_size;


    //check enum
    private Status StatusType;

    private Date create_date;


    private Date last_update;

    private Customer customer;



    private Set<CartItem> cartItems;

    public Long getCartId() {
       return id;
    }
}
