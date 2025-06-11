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
@Table(name = "countries")
public class Country {
    private Long id;
    private String country_name;
    private Date create_date;
    private Date last_update;
    private Set<Division> divisions;
}
