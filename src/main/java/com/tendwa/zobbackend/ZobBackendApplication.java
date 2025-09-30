package com.tendwa.zobbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZobBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZobBackendApplication.class, args);
    }

}
