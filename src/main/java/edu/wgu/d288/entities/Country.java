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
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Long id;

    @Column(name="country")
    private String country_name;
    @Column(name = "create_date")
    private Date create_date;


    @Column(name = "last_update")
    private Date last_update;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Division> divisions = new HashSet<>();
}
