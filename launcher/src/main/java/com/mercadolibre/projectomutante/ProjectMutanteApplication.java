package com.mercadolibre.projectomutante;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjectMutanteApplication {

    private static final Logger LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        LOGGER.info("Se ha iniciado el sistema...");
        SpringApplication.run(ProjectMutanteApplication.class, args);
    }
}
