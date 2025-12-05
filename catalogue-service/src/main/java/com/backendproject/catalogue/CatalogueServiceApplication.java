package com.backendproject.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.backendproject.catalogue.entity")
@EnableJpaRepositories("com.backendproject.catalogue.repository")
public class CatalogueServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }
}

