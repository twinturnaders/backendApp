package edu.wgu.d288.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "address")
    private String address;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "last_update")
    private Date last_update;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "phone")
    private String phone;


    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public Customer(Long customer_id, String address, Date create_date, String firstName, String lastName, Date last_update, String postal_code, String phone, Set<Cart> carts) {
        this.customer_id = customer_id;
        this.address = address;
        this.create_date = create_date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.last_update = last_update;
        this.postal_code = postal_code;
        this.phone = phone;
        this.carts = carts;
    }

    public Long getCustomerId() {
        return customer_id;
    }
}
