package com.mercadolibre.projectomutante.service;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.ports.spi.DNAPersistencePort;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DNAServiceImplTest {

    private DNAPersistencePort dnaPersistencePort = Mockito.mock(DNAPersistencePort.class);


    private DNAServiceImpl dnaService;

    private ValidateConditions validateConditions;

    @BeforeEach
    void setUp() {
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final DNADto dnaDto = new DNADto();
        dnaDto.setMutant(1);
        dnaDto.setDna(dna);
        Mockito.when(dnaPersistencePort.saveDNA(dnaDto)).thenReturn(dnaDto);

    }

    @Test
    void saveDNA() {
        init();
        try {

            final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
            final DNADto dnaMutantDto = new DNADto();
            dnaMutantDto.setMutant(1);
            dnaMutantDto.setDna(dna);
            Assertions.assertEquals(dnaService.saveDNA(dnaMutantDto).getMutant(), 1);
        } catch (Exception exception) {

        }


    }

    @Test
    void isExist(){
        init();
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final DNADto dnaMutantDto = new DNADto();
        dnaMutantDto.setMutant(1);
        dnaMutantDto.setDna(dna);
        Assertions.assertFalse(dnaService.isExist(dnaMutantDto));
    }


    private void init() {
        validateConditions = new ValidateConditions();
        dnaService = new DNAServiceImpl(dnaPersistencePort, validateConditions);
    }

    @Test
    void getStats() {
        init();

    }
}