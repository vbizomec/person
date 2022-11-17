package com.api.person.repository;

import com.api.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository <Person, Long> {

    List<Person> findPersonByCountry(String country); //returns all persons with by country
 /*   List<Person> findPersonContaining(String name); */

}
