package com.example.demo.models;

import javax.persistence.*;


@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private long salaryYearly;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Job(){}

    public Job(long id, String jobTitle, long salaryYearly, Person person) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.salaryYearly = salaryYearly;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Job(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public long getSalaryYearly() {
        return salaryYearly;
    }

    public void setSalaryYearly(long salaryYearly) {
        this.salaryYearly = salaryYearly;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
