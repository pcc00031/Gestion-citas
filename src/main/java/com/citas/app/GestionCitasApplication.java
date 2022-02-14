package com.citas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.citas.servicios",
        "com.citas.repositorios",
        "com.citas.controladorREST"
})

@EntityScan(basePackages = "com.citas.entidades")
@EnableJpaRepositories("com.citas.repositorios")
public class GestionCitasApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GestionCitasApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GestionCitasApplication.class);
    }

}