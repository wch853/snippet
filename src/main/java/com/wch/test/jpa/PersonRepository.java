package com.wch.test.jpa;

import com.wch.test.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPA操作对象
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByName(String name);

    @Modifying
    @Query(value = "INSERT INTO person(name, age) VALUES(?1, ?2)", nativeQuery = true)
    @Transactional
    int insertPersonByParam(String name, int age);

    int deleteById(int id);
}
