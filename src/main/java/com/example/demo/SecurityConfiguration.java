package com.example.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Which pages will require authentication?
    // Which pages are available to everyone?
    // What is the path to the login page?
    // What hashing algorithm will we use to store passwords?
    //(Remember we didn't specify Bcrypt in our UserController? Now we can specify which algorithm to use!)

    public PersonDetailsLoader personsLoader;
    // Fancy way of saying now we have supercharged PersonRepository, with way more functionality than a plain PersonRepository has

    // Set up our constructor for this configuration
    public SecurityConfiguration(PersonDetailsLoader personsLoader){this.personsLoader = personsLoader;}


}
