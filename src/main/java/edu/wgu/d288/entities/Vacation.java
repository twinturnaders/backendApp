package edu.wgu.d288.entities;

import jakarta.persistence.Table;
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
@Table(name = "vacations")
public class Vacation {
    private Long id;
    private String vacation_title;
    private String description;
    private BigDecimal travel_price;
    private String image_URL;
    private Date create_date;
    private Date last_update;
    private Set<Excursion> excursions;
}
