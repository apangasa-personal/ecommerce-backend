package com.backendproject.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.backendproject.catalogue",
        "com.backendproject.shared" // include shared-library entities
})
@EntityScan(basePackages = {
        "com.backendproject.catalogue.entity",
        "com.backendproject.shared.entity"
})
@EnableJpaRepositories(basePackages = "com.backendproject.catalogue.repository")
public class CatalogueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }
}
