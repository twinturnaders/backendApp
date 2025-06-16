package edu.wgu.d288;


import edu.wgu.d288.dao.CountryRepository;
import edu.wgu.d288.dao.DivisionRepository;
import edu.wgu.d288.entities.Country;
import edu.wgu.d288.entities.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


import edu.wgu.d288.dao.CustomerRepository;

import edu.wgu.d288.entities.Customer;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;


@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private DivisionRepository divisionRepo;

    @Autowired
    private CountryRepository countryRepo;


    @Override
    public void run(String... args) throws Exception {
        Date date = Date.from(Instant.now());

        Country country = countryRepo.getReferenceById(1L);


        Division division = new Division(null,"Twirly Tangerine Town", date, date, country, null);





        divisionRepo.save(division);





        List<Customer> customers = List.of(

                new Customer(null,"34 Doing Stuff", date,"Night", "Hawk", date, "51251", "2238675309",null),
                new Customer(null, "89 Making Stuff",date, "John", "Stamos", date, "52523", "3238675309",null),
                new Customer(null, "09 Bean Burrito",date,"Bob", "Saget", date,  "85854", "4238675309", null),
                new Customer(null,"67 Turtle Toes",date, "Taco", "Tuesday", date, "58720", "5238675309", null),
                new Customer(null, "2 Wallaby Way", date, "Betty", "White", date,"57845", "1238675309" ,null)
                );
        for (Customer customer : customers) {
            if (customerRepo.findByPhone(customer.getPhone()).isPresent()) {
                System.out.println("Customer with phone " + customer.getPhone() + " already exists");
            } else {
                customer.setDivision(division);
                customerRepo.save(customer);
                System.out.println("Saved new customer: " + customer.getFirstName());
            }
        }
    }


}


