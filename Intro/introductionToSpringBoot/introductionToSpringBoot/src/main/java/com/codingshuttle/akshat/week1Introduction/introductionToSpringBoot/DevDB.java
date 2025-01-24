package com.codingshuttle.akshat.week1Introduction.introductionToSpringBoot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "deploy.env", havingValue = "development")
public class DevDB implements DB{

    public String getData(){
        return "Hello! from the DevDB (I90)";
    }
}
