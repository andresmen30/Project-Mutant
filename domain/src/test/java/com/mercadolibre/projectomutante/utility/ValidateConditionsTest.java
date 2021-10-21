package com.mercadolibre.projectomutante.utility;

import com.mercadolibre.projectomutante.exceptions.FormatInvalidDNAException;
import com.mercadolibre.projectomutante.exceptions.SizeInvalidDNAException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateConditionsTest {

    private ValidateConditions validateConditions;

    @Test()
    void isDNACorrect() {
        validateConditions = new ValidateConditions();
        final String[] dnaSize = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTA", "TCACTG"};
        final String[] dnaFormat = {"ATGCGA", "CAGIGC", "KTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final String[] dnaCorrect = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final Exception exception = Assertions.assertThrows(SizeInvalidDNAException.class, () -> {
            validateConditions.isDNACorrect(dnaSize);
        });
        Assertions.assertTrue("Oops el tamaño de la secuencia del ADN no es correcto"
                .contains(exception.getMessage()));
        Assertions.assertTrue(validateConditions.isDNACorrect(dnaCorrect));
        final Exception exceptionFormat = Assertions.assertThrows(FormatInvalidDNAException.class, () -> {
            validateConditions.isDNACorrect(dnaFormat);
        });
        Assertions.assertTrue(("Oops la secuencia del ADN " +
                "del sujeto, no es acorde a la base nitrogenada establecida")
                .contains(exceptionFormat.getMessage()));

    }

    @Test
    void areEqualDna() {
        validateConditions = new ValidateConditions();
        final char a = 'T';
        final char b = 'T';
        final char c = 'T';
        final char d = 'T';
        Assertions.assertTrue(validateConditions.areEqualDna(a, b, c, d));

    }
}