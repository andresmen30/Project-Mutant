package com.mercadolibre.projectomutante.exceptions;


import com.mercadolibre.projectomutante.utility.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHelper extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getRootLogger();
    @Autowired
    private ResponseUtil responseUtil;

    public ExceptionHelper(final ResponseUtil responseUtil) {
        this.responseUtil = responseUtil;
    }

    @ExceptionHandler(value = {FieldMandatoryException.class})
    public ResponseEntity handleSqlException(final FieldMandatoryException ex) {
        LOGGER.error("FieldMandatoryException Exception:", ex);
        return responseUtil.resultError(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {FormatInvalidDNAException.class})
    public ResponseEntity formatException(final FormatInvalidDNAException ex) {
        LOGGER.error("FormatInvalidDNAException Exception:", ex);
        return responseUtil.resultError(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());

    }

    @ExceptionHandler(value = {SizeInvalidDNAException.class})
    public ResponseEntity sizeException(final SizeInvalidDNAException ex) {
        LOGGER.error("SizeInvalidDNAException Exception:", ex);
        return responseUtil.resultError(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());

    }

    @ExceptionHandler(value = {DNAException.class})
    public ResponseEntity DNAException(final DNAException ex) {
        LOGGER.error("DNAException Exception:", ex);
        return responseUtil.resultError(HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());

    }

    @ExceptionHandler(value = {RecordDuplicateException.class})
    public ResponseEntity duplicateException(final RecordDuplicateException ex) {
        LOGGER.error("RecordDuplicateException Exception:", ex);
        return responseUtil.resultError(HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
