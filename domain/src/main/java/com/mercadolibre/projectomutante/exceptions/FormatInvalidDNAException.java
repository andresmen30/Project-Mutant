package com.mercadolibre.projectomutante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormatInvalidDNAException extends RuntimeException {

    public FormatInvalidDNAException() {
        super("Oops la secuencia del ADN del sujeto, no es acorde a la base nitrogenada establecida");
    }

}
