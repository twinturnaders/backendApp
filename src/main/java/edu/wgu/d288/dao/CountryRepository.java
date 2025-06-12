package edu.wgu.d288.dao;

import edu.wgu.d288.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "countries", path="countries")
@CrossOrigin(origins = "http://localhost:4200")
public interface CountryRepository extends JpaRepository<Country, Long> {


}
