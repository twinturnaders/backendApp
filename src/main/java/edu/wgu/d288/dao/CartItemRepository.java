package edu.wgu.d288.dao;

import edu.wgu.d288.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "cart_items", path="cart_items")
@CrossOrigin(origins = "http://localhost:4200")
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
