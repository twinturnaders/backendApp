package edu.wgu.d288.entities;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "divisions")
public class Division {
    private Long id;
    private String division_name;
    private Date create_date;
    private Date last_update;
    private Country country;
}
