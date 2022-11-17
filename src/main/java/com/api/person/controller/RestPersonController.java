package com.api.person.controller;

import com.api.person.model.Person;
import com.api.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class RestPersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person person1 = personService.savePerson(new Person(person.getFirstName(), person.getLastName(), person.getAge(), person.getCountry()));
            return new ResponseEntity<>(person1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(@RequestParam(required = false) String country) {
        try {

            List<Person> persons;
            if (country == null) {
                persons = personService.getAllPersons();
            } else {
                //create a method that will filter by title
                persons = personService.getPersonByCountry(country);
            }
            // get a List of tutorials from database
            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id){

        Person person = personService.getPersonById(id);
        if(person != null){
            return  new ResponseEntity<>(person, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updateTutorial(@PathVariable("id") long id, @RequestBody Person person){

        Person person1= personService.updatePersonById(id, person);
        if (person1 !=null){
            return  new ResponseEntity<>(personService.savePerson(person1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        //delete one tutorial by id
        try {
            personService.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
