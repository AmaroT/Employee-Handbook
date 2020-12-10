package com.example.demo.repositories;

import com.example.demo.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobsRepository extends JpaRepository<Job, Long> {

    Job findByJobTitle(String jobTitle); // select * from jobs where title = ?


    //Following method shows you how to use named parameters in a HQL custom query
    @Query("FROM Job j WHERE j.jobTitle LIKE %:term%")
    List<Job> searchByJobTitle(@Param("term") String term);

}
