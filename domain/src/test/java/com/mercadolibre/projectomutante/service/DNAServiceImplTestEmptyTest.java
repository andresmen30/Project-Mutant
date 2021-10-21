package com.mercadolibre.projectomutante.service;

import com.mercadolibre.projectomutante.ports.spi.DNAPersistencePort;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

class DNAServiceImplTestEmptyTest {

    @Autowired
    private DNAPersistencePort dnaPersistencePort = Mockito.mock(DNAPersistencePort.class);


    private DNAServiceImpl dnaService;

    private ValidateConditions validateConditions;

    @BeforeEach
    void setUp() {
        Mockito.when(dnaPersistencePort.getStats()).thenReturn(new ArrayList<>());
    }

    @Test
    void getStats() {
        init();
        Assertions.assertTrue(dnaService.getStats().getCountHumantDna() == 0);
        Assertions.assertTrue(dnaService.getStats().getCountMutantDna() == 0);
        Assertions.assertTrue(dnaService.getStats().getRatio() == 0);
    }

    private void init() {
        validateConditions = new ValidateConditions();
        dnaService = new DNAServiceImpl(dnaPersistencePort, validateConditions);
    }
}