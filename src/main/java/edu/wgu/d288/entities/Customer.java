package edu.wgu.d288.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "customer_first_name")
    private String first_name;

    @Column(name = "customer_last_name")
    private String last_name;

    @Column(name = "last_update")
    private Date last_update;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "phone")
    private String phone;


    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer_id", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public Long getCustomerId() {
        return id;
    }
}
