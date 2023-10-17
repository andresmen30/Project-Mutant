package com.mercadolibre.projectomutante.configuration;

import com.mercadolibre.projectomutante.adapters.DNAJpaAdapter;
import com.mercadolibre.projectomutante.ports.api.DNAServicePort;
import com.mercadolibre.projectomutante.ports.spi.DNAPersistencePort;
import com.mercadolibre.projectomutante.service.DNAServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DNAConfig {

    @Bean
    public DNAPersistencePort DNAPersistence() {
        return new DNAJpaAdapter();
    }

    @Bean
    public DNAServicePort DNAService() {
        return new DNAServiceImpl(DNAPersistence());
    }
}
