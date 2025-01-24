package com.codingshuttle.akshat.week1Introduction.Week1Homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week1Homework implements CommandLineRunner {

    @Autowired
    CakeBaker cb;

    public static void main(String[] args) {
        SpringApplication.run(Week1Homework.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cb.bakeCake();
    }

}
