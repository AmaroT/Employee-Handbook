package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length =100)
    private String password;

    @Column(columnDefinition = "TEXT")
    private String profilePic;

    @Column(nullable = false, length = 2)
    private int age;

    @Column(nullable= false, length = 100)
    private Date dateJoined;

    @Column(nullable = false, length = 100)
    private Date dateUpdated;
//    Relationship between Person and Jobs
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Job> jobs;

//Constructor

public Person() {}

    public Person(long id, String name, int age, Date dateJoined, Date dateUpdated, String password, String profilePic, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.profilePic = profilePic;
        this.age = age;
        this.dateJoined = dateJoined;
        this.dateUpdated = dateUpdated;
        this.jobs = jobs;
    }
//    Implementing the Copy Constructor



    public Person(Person copy) {
        this.id = copy.id;
        this.name = copy.name;
        this.password = copy.password;
        this.profilePic = copy.profilePic;
        this.age = copy.age;
        this.dateJoined = copy.dateJoined;
        this.dateUpdated = copy.dateUpdated;
        this.jobs = copy.jobs;
    }
//    Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() { return profilePic;}

    public void setProfilePic(String profilePic) {this.profilePic = profilePic;}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}

