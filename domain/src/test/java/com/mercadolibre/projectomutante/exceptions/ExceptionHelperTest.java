package com.mercadolibre.projectomutante.exceptions;

import com.mercadolibre.projectomutante.utility.ResponseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionHelperTest {

    private ExceptionHelper exceptionHelper;
    private ResponseUtil responseUtil;

    @Test
    void handleSqlException() {
        init();
        Assertions.assertEquals(exceptionHelper
                .handleSqlException(new FieldMandatoryException("Campos mandatorio")).getStatusCodeValue(), 400);

    }

    @Test
    void formatException() {
        init();
        Assertions.assertEquals(exceptionHelper
                .formatException(new FormatInvalidDNAException()).getStatusCodeValue(), 400);
    }

    @Test
    void sizeException() {
        init();
        Assertions.assertEquals(exceptionHelper
                .sizeException(new SizeInvalidDNAException()).getStatusCodeValue(), 400);
    }

    @Test
    void DNAException() {
        init();
        Assertions.assertEquals(exceptionHelper
                .DNAException(new DNAException(new Exception())).getStatusCodeValue(), 500);
    }

    @Test
    void duplicateException() {
        init();
        Assertions.assertEquals(exceptionHelper
                .duplicateException(new RecordDuplicateException()).getStatusCodeValue(), 400);
    }

    private void init() {
        responseUtil = new ResponseUtil();
        exceptionHelper = new ExceptionHelper(responseUtil);
    }
}