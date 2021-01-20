package com.example.demo.controllers;

import com.example.demo.models.Job;
import com.example.demo.models.Person;
import com.example.demo.repositories.JobsRepository;
import com.example.demo.repositories.PersonRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class PersonController {
    private final PersonRepository personRepo;
    private final PasswordEncoder passwordEncoder;
    private final JobsRepository jobsRepo;

//    Constructor

    public PersonController(PersonRepository personRepo, PasswordEncoder passwordEncoder, JobsRepository jobsRepo) {
        this.personRepo = personRepo;
        this.passwordEncoder = passwordEncoder;
        this.jobsRepo = jobsRepo;
    }

    //Want the user to be able to access the sign-up page
    @GetMapping("/create")
    public String createPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person/create";
    }

    //What happens when a new user submits the register form?
    @PostMapping("/sign-up")
    public String savePerson(@ModelAttribute Person person, @RequestParam(name = "confirmPassword") String confirmPassword,
                             @RequestParam(name = "password") String password) {
        if (!person.getPassword().equals(confirmPassword)) {
            return "redirect:/create";
        }
        String hash = passwordEncoder.encode(person.getPassword()); //plaintext password
        person.setPassword(hash); //Hashed
        person.setProfilePic("img/default-profile-icon.jpg");
        if (person.getId() == 0) {
            personRepo.save(person);
            return "redirect:/login";
        } else {
            personRepo.save(person);
            return "redirect:/profile";
        }
    }

    // redirecting login user into their profile page
    @GetMapping("/profile")
    public String profilePage(Model model) {
        Person getPerson = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("person", personRepo.getOne(getPerson.getId()));
        model.addAttribute("photoUrl", personRepo.getOne(getPerson.getId()).getProfilePic());
        return "person/profile";
    }
    //user's Profile pic area
    @PostMapping("/profile/pic")
    public String saveProfile(@RequestParam long personId, @RequestParam String url, @ModelAttribute Person person){
        Person saveProfile = personRepo.getOne(personId);
        saveProfile.setProfilePic(url);
        personRepo.save(saveProfile);
        return "redirect:/profile";
    }
    // editing user profile information like bio and job
    @GetMapping("/users/edit/{id}")
    public String EditProfile(@PathVariable long id, Model model){
        model.addAttribute("editPerson", personRepo.getOne(id));
        List<Job> jobList = jobsRepo.findAll();
        model.addAttribute("jobList", jobList);
        return "person/edit";
    }
}
