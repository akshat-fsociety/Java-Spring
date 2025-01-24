package com.codingshuttle.akshat.week1Introduction.Week1Homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CakeBaker {

    @Autowired
    private Frosting f;
    @Autowired
    private Syrup s;

    void bakeCake() {
        System.out.println("Baking the Cake with " + f.getFrostingType() +" and " + s.getSyrupType());
    }

}
