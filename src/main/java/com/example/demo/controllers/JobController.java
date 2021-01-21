package com.example.demo.controllers;

import com.example.demo.models.Job;
import com.example.demo.repositories.JobsRepository;
import com.example.demo.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JobController {
    private final JobsRepository jobsRepo;
    private final PersonRepository personRepo;

    public JobController(JobsRepository jobsRepo, PersonRepository personRepo) {
        this.jobsRepo = jobsRepo;
        this.personRepo = personRepo;
    }
    @GetMapping("/jobs")
    public String showJobs(Model model){
        List<Job> jobs = jobsRepo.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs/jobs";
    }

    @GetMapping("/jobs/search")
    public String searchJob(@RequestParam String term, Model model){
        List<Job> jobs = jobsRepo.searchByJobTitle(term);
        model.addAttribute("jobs", jobs);
        return "jobs/search";
    }
}
