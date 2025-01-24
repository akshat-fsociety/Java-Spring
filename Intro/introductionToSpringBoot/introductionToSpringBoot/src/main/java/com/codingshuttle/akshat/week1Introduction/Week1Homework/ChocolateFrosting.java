package com.codingshuttle.akshat.week1Introduction.Week1Homework;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.type", havingValue = "chocolate")
public class ChocolateFrosting implements Frosting{

    @Override
    public String getFrostingType() {
//        System.out.println("Chocolate Frosting Added");
        return "Chocolate Frosting";
    }

}
