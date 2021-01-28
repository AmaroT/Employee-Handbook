package com.example.demo.services;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;

import javax.naming.NameNotFoundException;

public class PersonDetailsLoader implements PersonDetailsService {
    private final PersonRepository persons;

    public PersonDetailsLoader(PersonRepository persons) {this.persons = persons;}

    @Override
    public PersonDetails loadPersonByName(String name) throws NameNotFoundException {
        Person person = persons.findPersonByName(name);
        if (person == null) {
            throw new PersonNotFoundException("Person was not found by Name: " + name);
        }
        return (PersonDetails) new PersonWithRoles(person)

    }

}
