package com.app.projectjar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectJarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectJarApplication.class, args);
    }

}
