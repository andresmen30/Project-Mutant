package com.mercadolibre.projectomutante.controller;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.exceptions.DNAException;
import com.mercadolibre.projectomutante.exceptions.FieldMandatoryException;
import com.mercadolibre.projectomutante.ports.api.DNAServicePort;
import com.mercadolibre.projectomutante.utility.ResponseUtil;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class DNAControllerHumanTest {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private DNAServicePort dnaServicePort = Mockito.mock(DNAServicePort.class);

    @Autowired
    private ValidateConditions validateConditions;

    @Autowired
    private DNAController dnaController;


    @BeforeEach
    void setUp() {
        final String[] dnaHuman = {"ATGCGA", "CAGCGC", "CTATGT", "AGGTGG", "CAACCG", "TCACTG"};
        final DNADto dnaDto = new DNADto();
        dnaDto.setMutant(0);
        dnaDto.setDna(dnaHuman);
        try {
            Mockito.when(dnaServicePort.saveDNA(dnaDto)).thenReturn(dnaDto);
        } catch (final Exception exception) {

        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void postADN() {
        validateConditions = new ValidateConditions();
        responseUtil = new ResponseUtil();
        dnaController = new DNAController(responseUtil, dnaServicePort, validateConditions);
        final String[] dnaHuman = {"ATGCGA", "CAGCGC", "CTATGT", "AGGTGG", "CAACCG", "TCACTG"};
        final DNADto dnaMutantDto = new DNADto();
        dnaMutantDto.setMutant(0);
        dnaMutantDto.setDna(dnaHuman);
        Assertions.assertEquals(dnaController.postADN(dnaMutantDto).getStatusCodeValue(),
                HttpStatus.FORBIDDEN.value());

        final String[] dnaFormat = {"ATGCGA", "CAGIGC", "KTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final DNADto dnaFormatDto = new DNADto();
        dnaFormatDto.setMutant(0);
        dnaFormatDto.setDna(dnaFormat);
        final Exception exception = Assertions.assertThrows(DNAException.class, () -> {
            dnaController.postADN(dnaFormatDto);
        });
        Assertions.assertTrue("com.mercadolibre.projectomutante.exceptions.DNAException"
                .contains(exception.getClass().getName()));
        final DNADto dnaMandatory= new DNADto();
        dnaMandatory.setMutant(0);
        dnaMandatory.setDna(null);
        final Exception exceptionMandatory = Assertions.assertThrows(FieldMandatoryException.class, () -> {
            dnaController.postADN(dnaMandatory);
        });
        Assertions.assertTrue("Oops no se ha enviado ningun ADN a validar"
                .contains(exceptionMandatory.getMessage()));


    }
}