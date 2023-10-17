package com.mercadolibre.projectomutante.utility;

import com.mercadolibre.projectomutante.exceptions.FormatInvalidDNAException;
import com.mercadolibre.projectomutante.exceptions.SizeInvalidDNAException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidateConditions {

    private static final String ADN_VALIDATE = "[ACGT]+";


    public boolean isDNACorrect(final String... dna) {
        int dnaSize = dna.length;
        for (final String dnaRequest : dna) {
            final String dnaTrim = StringUtils.trim(dnaRequest);
            if (dnaTrim.length() != dnaSize) {
                throw new SizeInvalidDNAException();
            }
            if (!StringUtils.trim(dnaRequest).matches(ADN_VALIDATE)) {
                throw new FormatInvalidDNAException();
            }
        }
        return true;
    }

    public boolean areEqualDna(char a, char b, char c, char d) {
        return a == b & b == c & c == d;
    }
}
