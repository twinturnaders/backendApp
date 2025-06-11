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
@Table(name = "customers")
public class Customer {

    private Long id;

    private String firstName;

    private String lastName;


    private String address;
    private String postal_code;
    private String phone;
    private Date create_date;
    private Date last_update;
    private Division division;
    private Set<Cart> carts;

    public Long getCustomerId() {
        return id;
    }
}
