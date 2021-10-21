package com.mercadolibre.projectomutante.service;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.ports.spi.DNAPersistencePort;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

class DNAServiceImplExceptionsTest {

    @Autowired
    private DNAPersistencePort dnaPersistencePort = Mockito.mock(DNAPersistencePort.class);


    private DNAServiceImpl dnaService;

    private ValidateConditions validateConditions;

    @BeforeEach
    void setUp() {
        final List<DNADto> list = getList();
        Mockito.when(dnaPersistencePort.getStats()).thenReturn(list);

    }


    private void init() {
        validateConditions = new ValidateConditions();
        dnaService = new DNAServiceImpl(dnaPersistencePort, validateConditions);
    }

    @Test
    void getStats() {
        init();
        Assertions.assertTrue(dnaService.getStats().getCountHumantDna()> 0);
        Assertions.assertTrue(dnaService.getStats().getCountMutantDna()> 0);
        Assertions.assertTrue(dnaService.getStats().getRatio()> 0);
    }

    private List<DNADto> getList(){
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final List<DNADto> list = new ArrayList<>();
        list.add(new DNADto(dna, 0));
        list.add(new DNADto(dna, 0));
        list.add(new DNADto(dna, 1));
        list.add(new DNADto(dna, 1));
        list.add(new DNADto(dna, 1));
        return list;
    }


}