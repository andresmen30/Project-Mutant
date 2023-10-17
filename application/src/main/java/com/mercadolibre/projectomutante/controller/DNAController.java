package com.mercadolibre.projectomutante.controller;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.exceptions.DNAException;
import com.mercadolibre.projectomutante.exceptions.FieldMandatoryException;
import com.mercadolibre.projectomutante.exceptions.RecordDuplicateException;
import com.mercadolibre.projectomutante.ports.api.DNAServicePort;
import com.mercadolibre.projectomutante.utility.ResponseUtil;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DNAController {


    private ResponseUtil responseUtil;


    private DNAServicePort dnaServicePort;


    private ValidateConditions validateConditions;


    private static final Logger LOGGER = LogManager.getRootLogger();

    @Autowired
    public DNAController(final ResponseUtil responseUtil,
                         final DNAServicePort dnaServicePort, final ValidateConditions validateConditions) {
        this.responseUtil = responseUtil;
        this.dnaServicePort = dnaServicePort;
        this.validateConditions = validateConditions;
    }


    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postADN(@RequestBody final DNADto dnaDto) {
        if (dnaDto == null || dnaDto.getDna() == null
                || dnaDto.getDna().length == 0) {
            throw new FieldMandatoryException("Oops no se ha enviado ningun ADN a validar");
        }
        LOGGER.info("Se ha iniciado la verificación del sujeto, secuencia de ADN -> {}", dnaDto.getDna());
        if (dnaServicePort.isExist(dnaDto)) {
            throw new RecordDuplicateException();
        }
        try {
            if (validateConditions.isDNACorrect(dnaDto.getDna())) {
                dnaServicePort.saveDNA(dnaDto);
            }
        } catch (final Exception exception) {
            throw new DNAException(exception);
        }
        return dnaDto.getMutant() == NumberUtils.INTEGER_ONE ? responseUtil.resultOk("Eres uno de los nuestros, bienvenido al equipo mutante")
                : responseUtil.resultForbidden("Los humanos no son bienvenidos acá, sin embargo te tendremos en cuenta");
    }


}
