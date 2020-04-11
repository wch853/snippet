package com.wch.snippet.simple;

import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Test
public class SimpleTest {

    public void test() throws IOException, InterruptedException {
        List<Object> objects = Lists.newArrayList();
        while (true) {
            for (int i = 0; i < 10000; i++) {
                objects.add(new Object());
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}