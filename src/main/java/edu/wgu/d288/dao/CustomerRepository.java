package edu.wgu.d288.dao;

import edu.wgu.d288.entities.Country;
import edu.wgu.d288.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "customers", path="customers")
@CrossOrigin(origins = "http://localhost:4200")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Object> findByPhone(String phone);
}
