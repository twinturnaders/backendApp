package edu.wgu.d288.entities;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")


public class CartItem {
    private Long id;
    private Vacation vacation;
    private Set<Excursion> excursions;
    private Cart cart;
    private Date create_date;
    private Date last_update;

}
