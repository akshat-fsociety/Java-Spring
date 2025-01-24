package com.codingshuttle.akshat.week1Introduction.introductionToSpringBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    Apple getApple(){
        return new Apple();
    }
}
