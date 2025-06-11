package edu.wgu.d288.dao;

import edu.wgu.d288.entities.Customer;
import edu.wgu.d288.entities.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "excursions", path="excursions")
@CrossOrigin(origins = "http://localhost:4200")
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
