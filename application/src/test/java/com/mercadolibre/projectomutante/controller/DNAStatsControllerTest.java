package com.mercadolibre.projectomutante.controller;

import com.mercadolibre.projectomutante.data.ResultStats;
import com.mercadolibre.projectomutante.ports.api.DNAServicePort;
import com.mercadolibre.projectomutante.utility.ResponseUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class DNAStatsControllerTest {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private DNAServicePort dnaServicePort = Mockito.mock(DNAServicePort.class);


    @Autowired
    private DNAStatsController dnaStatsController;


    @BeforeEach
    void setUp() {
        final ResultStats resultStats = new ResultStats();
        resultStats.setCountHumantDna(40);
        resultStats.setCountMutantDna(100);
        resultStats.setRatio(0.4);
        try {
            Mockito.when(dnaServicePort.getStats()).thenReturn(resultStats);
        } catch (final Exception exception) {

        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStats() {
        responseUtil = new ResponseUtil();
        dnaStatsController = new DNAStatsController(dnaServicePort, responseUtil);
        Assertions.assertEquals(dnaStatsController.getStats().getStatusCodeValue(),
                HttpStatus.OK.value());

    }
}