package com.mercadolibre.projectomutante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DNAException extends RuntimeException {


    public DNAException(final Exception exception) {

        super(exception);
    }}
