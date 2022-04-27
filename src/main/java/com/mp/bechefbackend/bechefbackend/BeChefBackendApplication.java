package com.mp.bechefbackend.bechefbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) //TODO: Quita la seguridad - TEMPORAL!!!
public class BeChefBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeChefBackendApplication.class, args);
    }

}
