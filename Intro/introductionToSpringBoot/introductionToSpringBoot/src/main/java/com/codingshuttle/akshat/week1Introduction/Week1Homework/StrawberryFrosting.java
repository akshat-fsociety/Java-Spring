package com.codingshuttle.akshat.week1Introduction.Week1Homework;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.type", havingValue = "strawberry")
public class StrawberryFrosting implements Frosting{

    @Override
    public String getFrostingType() {
//        System.out.println("Strawberry Frosting Added");
        return "Strawberry Frosting";
    }

}
