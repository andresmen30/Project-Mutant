package com.mercadolibre.projectomutante.service;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.ports.spi.DNAPersistencePort;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

class DNAServiceImplStatsTest {

    @Autowired
    private DNAPersistencePort dnaPersistencePort = Mockito.mock(DNAPersistencePort.class);


    private DNAServiceImpl dnaService;

    private ValidateConditions validateConditions;

    @BeforeEach
    void setUp() {
        final DNADto dnaDto = getDto();
        Mockito.when(dnaPersistencePort.saveDNA(dnaDto)).thenReturn(dnaDto);

    }


    private void init() {
        validateConditions = new ValidateConditions();
        dnaService = new DNAServiceImpl(dnaPersistencePort, validateConditions);
    }

    @Test
    void saveDNA() {
        init();
        try {
            final DNADto dnaMutantDto = getDto();
            Assertions.assertEquals(dnaService.saveDNA(dnaMutantDto).getMutant(), 1);
        } catch (Exception exception) {

        }


    }

    private DNADto getDto() {
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final DNADto dnaMutantDto = new DNADto();
        dnaMutantDto.setMutant(1);
        dnaMutantDto.setDna(dna);
        return dnaMutantDto;
    }


}