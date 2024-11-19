package com.minbashi.project;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class MinbashiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinbashiApplication.class, args);
    }
}
