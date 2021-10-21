package com.mercadolibre.projectomutante.controller;

import com.mercadolibre.projectomutante.data.ResultStats;
import com.mercadolibre.projectomutante.exceptions.DNAException;
import com.mercadolibre.projectomutante.ports.api.DNAServicePort;
import com.mercadolibre.projectomutante.utility.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DNAStatsController {

    @Autowired
    private DNAServicePort dnaServicePort;

    @Autowired
    private ResponseUtil responseUtil;

    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    public DNAStatsController(final DNAServicePort dnaServicePort,
                              final ResponseUtil responseUtil){
        this.dnaServicePort = dnaServicePort;
        this.responseUtil = responseUtil;

    }

    @GetMapping(path = "/stats")
    public ResponseEntity getStats() {
        try {
            LOGGER.info("Obteniendo estadisticas");
            final ResultStats resultStats = dnaServicePort.getStats();
            return responseUtil.resultStatus(HttpStatus.OK, "Success", resultStats);
        } catch (final Exception exception) {
            throw new DNAException(exception);
        }


    }


}
