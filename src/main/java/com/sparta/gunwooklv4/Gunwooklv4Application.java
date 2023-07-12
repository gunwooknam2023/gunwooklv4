package com.sparta.gunwooklv4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Gunwooklv4Application {

    public static void main(String[] args) {
        SpringApplication.run(Gunwooklv4Application.class, args);
    }

}
