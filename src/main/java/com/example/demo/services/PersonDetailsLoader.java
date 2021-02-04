package com.example.demo.services;

import com.example.demo.models.Person;
import com.example.demo.models.PersonWithRoles;
import com.example.demo.repositories.PersonRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PersonDetailsLoader implements PersonDetailsService {
    private final PersonRepository persons; // gives us access to the "Claw" of grabbing/manipulating Person Objects

    public PersonDetailsLoader(PersonRepository persons) {this.persons = persons;}

    //PersonDetails interface allows us to get password / username/ check if account is expired, locked, or credentials expired
    @Override
    public PersonDetails loadPersonByUsername(String userName) throws UsernameNotFoundException {
        Person person = persons.findPersonByUserName(userName);

        //check to see if we actually found a Person username or not
        if(person == null) {
            //Now our console log output will be more detailed, in order for us to fine the problem  more quickly, because we know exactly that this had to do with not finding that username in the DB
            throw new UsernameNotFoundException("Person was not found for username: " + userName);
            //this tells our Java app to thorw the exception immediately
        }
        return (PersonDetails) new PersonWithRoles(person); // this will return the results of sending the 'user' object INTO a ' PersonWithRoles' constructor, and getting the output from using that constructor
    }
}
