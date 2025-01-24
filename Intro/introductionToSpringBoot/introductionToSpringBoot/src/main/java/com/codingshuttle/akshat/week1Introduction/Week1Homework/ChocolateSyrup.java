package com.codingshuttle.akshat.week1Introduction.Week1Homework;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.type", havingValue = "chocolate")
public class ChocolateSyrup implements Syrup{

    @Override
    public String getSyrupType() {
//        System.out.println("Chocolate Syrup Added");
        return "Chocolate Syrup";
    }

}
