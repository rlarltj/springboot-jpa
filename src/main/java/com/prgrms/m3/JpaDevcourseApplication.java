package com.prgrms.m3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class JpaDevcourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaDevcourseApplication.class, args);
    }

}
