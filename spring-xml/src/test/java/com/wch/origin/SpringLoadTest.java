package com.wch.origin;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * @author wch
 */
@Test
public class SpringLoadTest {

    public void loadTest() {
        new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
    }
}
