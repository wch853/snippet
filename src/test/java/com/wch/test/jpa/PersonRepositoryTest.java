package com.wch.test.jpa;

import com.wch.test.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Resource
    private PersonRepository personRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Test
    public void findByName() {
        List<Person> persons = personRepository.findByName("wch");
        LOGGER.info("persons: {}", persons);
    }

    @Test
    public void insertPersonByParam() {
        int count = personRepository.insertPersonByParam("wch", 21);
        LOGGER.info("count: {}", count);
    }

    @Test
    public void deleteById() {
        int count = personRepository.deleteById(1);
        LOGGER.info("count: {}", count);
    }
}