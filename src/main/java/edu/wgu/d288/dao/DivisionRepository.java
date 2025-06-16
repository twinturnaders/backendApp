package edu.wgu.d288.dao;

import edu.wgu.d288.entities.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "divisions", path="divisions")
@CrossOrigin(origins = "http://localhost:4200")
public interface DivisionRepository extends JpaRepository<Division, Long> {



}
