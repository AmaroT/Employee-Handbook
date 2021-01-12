package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.repositories.JobsRepository;
import com.example.demo.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {
    private final PersonRepository personRepo;
    private final JobsRepository jobsRepo;

//    Constructor

    public PersonController(PersonRepository personRepo, JobsRepository jobsRepo) {
        this.personRepo = personRepo;
        this.jobsRepo = jobsRepo;
    }

    //Want the user to be able to access the sign-up page
    @GetMapping("/create")
    public String createPerson(Model model){
        model.addAttribute("person", new Person());
        return "person/create";
    }
    //What happens when a new user submits the register form?
    @PostMapping("/sign-up")
    public String savePerson(@ModelAttribute Person person, @RequestParam(name = "confirmPassword") String confirmPassword,
                             @RequestParam(name = "password") String password) {
        if (!person.getPassword().equals(confirmPassword)) {
            return "redirect:/login";
        }
        String hash = passwordEncoder.encode(person.getPassword()); //plaintext password

    }
}
