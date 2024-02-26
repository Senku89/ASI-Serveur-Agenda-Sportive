package fr.upjv.agendasportive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"fr.upjv.applicationsportive", "fr.upjv.agendasportive.models"})
public class AgendaSportiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgendaSportiveApplication.class, args);
    }
}