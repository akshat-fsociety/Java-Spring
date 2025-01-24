package com.codingshuttle.akshat.week1Introduction.Week1Homework;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.type", havingValue = "strawberry")
public class StrawberrySyrup implements Syrup{

    @Override
    public String getSyrupType() {
//        System.out.println("Strawberry Syrup Added");
        return "Strawberry Syrup";
    }

}
