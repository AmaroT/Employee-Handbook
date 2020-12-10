package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.repositories.JobsRepository;
import com.example.demo.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
    private final PersonRepository personRepo;
    private final JobsRepository jobsRepo;

//    Constructor

    public PersonController(PersonRepository personRepo, JobsRepository jobsRepo) {
        this.personRepo = personRepo;
        this.jobsRepo = jobsRepo;
    }


    @GetMapping("/create")
    public String createPerson(Model model){
        model.addAttribute("person", new Person());
        return "person/create";
    }
}
