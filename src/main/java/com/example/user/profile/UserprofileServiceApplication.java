package com.example.user.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.example"}, exclude = {MongoAutoConfiguration.class})
public class UserprofileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserprofileServiceApplication.class, args);
    }

}
