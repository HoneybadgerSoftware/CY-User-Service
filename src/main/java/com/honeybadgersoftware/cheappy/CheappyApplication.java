package com.honeybadgersoftware.cheappy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CheappyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheappyApplication.class, args);
    }

}
