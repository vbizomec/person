package com.api.person.service;

import com.api.person.model.Person;
import com.api.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getPersonByCountry(String country) {
        return personRepository.findPersonByCountry(country);
    }

    @Override
    public Person getPersonById(long id) {
        Optional<Person> personData = personRepository.findById(id);
        if (personData.isPresent()) {
            return personData.get();
        }
        return null;
    }

    @Override
    public Person updatePersonById(long id, Person person) {
        Person personData = getPersonById(id);

        if(personData != null) {
            personData.setFirstName(person.getFirstName());
            personData.setLastName(person.getLastName());
            personData.setAge(person.getAge());
            personData.setCountry(person.getCountry());

            return personData;
        }
        return null ;
    }

    @Override
    public void deletePersonById(long id) {
     personRepository.deleteById(id);
    }
}
