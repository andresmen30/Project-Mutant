package com.mercadolibre.projectomutante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecordDuplicateException extends RuntimeException {

    public RecordDuplicateException() {
        super("Oops ya se encuentra registrado el ADN");
    }
}
