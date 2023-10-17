package com.mercadolibre.projectomutante.controller;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.ports.api.DNAServicePort;
import com.mercadolibre.projectomutante.utility.ResponseUtil;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DNAControllerMutantTest {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private DNAServicePort dnaServicePort = mock(DNAServicePort.class);

    @Autowired
    private ValidateConditions validateConditions;

    @Autowired
    private DNAController dnaController;


    @BeforeEach
    void setUp() {
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final DNADto dnaDto = new DNADto();
        dnaDto.setMutant(1);
        dnaDto.setDna(dna);
        try {
            when(dnaServicePort.saveDNA(dnaDto)).thenReturn(dnaDto);
        } catch (final Exception exception) {

        }
    }


    @Test
    void postADN() {
        init();
        final String[] dnaMutant = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final DNADto dnaMutantDto = new DNADto();
        dnaMutantDto.setMutant(1);
        dnaMutantDto.setDna(dnaMutant);
        Assertions.assertEquals(dnaController.postADN(dnaMutantDto).getStatusCodeValue(),
                HttpStatus.OK.value());

    }


    private void init() {
        validateConditions = new ValidateConditions();
        responseUtil = new ResponseUtil();
        dnaController = new DNAController(responseUtil, dnaServicePort, validateConditions);

    }
}