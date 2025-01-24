package com.codingshuttle.akshat.week1Introduction.introductionToSpringBoot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/*Because I'm using using the configuration annotation
therefore I've commented Component otherwise error that 2 beans found*/

//@Component
public class Apple {

    void eatApple(){
        System.out.println("Eating Apple 🍎");
    }

    @PostConstruct
    void CallThisBeforeTheAppleIsCreated(){
        System.out.println("Calling this before the Apple is created ⏫");
    }

    @PreDestroy
    void CallThisBeforeDestroy(){
        System.out.println("Destroying the Apply Bean ❌");
    }

}


