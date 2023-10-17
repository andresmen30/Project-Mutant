package com.mercadolibre.projectomutante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SizeInvalidDNAException extends RuntimeException {

    public SizeInvalidDNAException() {
        super("Oops el tamaño de la secuencia del ADN no es correcto");
    }

}
