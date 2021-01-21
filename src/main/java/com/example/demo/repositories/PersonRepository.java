package com.example.demo.repositories;

import com.example.demo.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // Need to be able to search for person by Person's name, as in Integration Test
    Person findPersonByName(String name);

    Person findById(long id);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    List<Person> findByNameLike(@Param("personTerm")String personTerm);
}
