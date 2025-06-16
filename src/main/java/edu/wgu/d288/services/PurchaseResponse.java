package edu.wgu.d288.services;


import edu.wgu.d288.dao.CartRepository;
import edu.wgu.d288.entities.Cart;
import edu.wgu.d288.entities.Status;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {
    private String orderTrackingNumber;
    private Long id;
    private BigDecimal package_price;
    private Integer party_size;
    private Status status;
    private Date create_date;
    private Date last_update;
}
