package com.mercadolibre.projectomutante.utility;


import com.mercadolibre.projectomutante.data.ResultDataProcess;
import com.mercadolibre.projectomutante.data.ResultStats;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ResponseUtil implements Serializable {

    public ResponseEntity resultOk(final String message) {
        return new ResponseEntity<>(new ResultDataProcess(HttpStatus.OK.value(),
                message, null, null), HttpStatus.OK);
    }

    public ResponseEntity resultForbidden(final String message) {
        return new ResponseEntity<>(new ResultDataProcess(HttpStatus.FORBIDDEN.value(),
                message, null, null), HttpStatus.FORBIDDEN);
    }

    public ResponseEntity resultError(final HttpStatus httpStatus, final int code, final String message) {
        return new ResponseEntity<>(new ResultDataProcess(code,
                message, null, null), httpStatus);
    }

    public ResponseEntity resultStatus(final HttpStatus httpStatus, final String message, final ResultStats resultStats) {
        return new ResponseEntity<>(new ResultDataProcess(HttpStatus.OK.value(),
                message, null, resultStats), httpStatus);
    }


}
