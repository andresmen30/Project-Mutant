package com.mercadolibre.projectomutante.service;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.data.ResultStats;
import com.mercadolibre.projectomutante.exceptions.RecordDuplicateException;
import com.mercadolibre.projectomutante.ports.api.DNAServicePort;
import com.mercadolibre.projectomutante.ports.spi.DNAPersistencePort;
import com.mercadolibre.projectomutante.utility.ValidateConditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DNAServiceImpl implements DNAServicePort {

    private final DNAPersistencePort dnaPersistencePort;

    @Autowired
    private ValidateConditions validateConditions;


    public DNAServiceImpl(final DNAPersistencePort dnaPersistencePort) {
        this.dnaPersistencePort = dnaPersistencePort;
    }

    public DNAServiceImpl(final DNAPersistencePort dnaPersistencePort,
                          ValidateConditions validateConditions) {
        this.dnaPersistencePort = dnaPersistencePort;
        this.validateConditions = validateConditions;
    }


    @Override
    public boolean isExist(final DNADto dnaDto) {
        return dnaPersistencePort.recordExist(dnaDto);
    }


    @Override
    public DNADto saveDNA(final DNADto dnaDto) {
        final boolean isMutant = isMutant(dnaDto.getDna());
        dnaDto.setMutant(isMutant ? NumberUtils.INTEGER_ONE : NumberUtils.INTEGER_ZERO);
        return this.dnaPersistencePort.saveDNA(dnaDto);
    }

    @Override
    public ResultStats getStats() {
        final List<DNADto> dnaDtos = this.dnaPersistencePort.getStats();
        final ResultStats resultStats = new ResultStats();
        if (CollectionUtils.isEmpty(dnaDtos)) {
            resultStats.setCountHumantDna(NumberUtils.INTEGER_ZERO);
            resultStats.setCountMutantDna(NumberUtils.INTEGER_ZERO);
            resultStats.setRatio(NumberUtils.INTEGER_ZERO);
        } else {
            resultStats.setCountHumantDna(dnaDtos.stream().filter(dna -> dna.getMutant() == NumberUtils.INTEGER_ZERO).count());
            resultStats.setCountMutantDna(dnaDtos.stream().filter(dna -> dna.getMutant() == NumberUtils.INTEGER_ONE).count());
            resultStats.setRatio(resultRatio(resultStats.getCountMutantDna(),
                    resultStats.getCountHumantDna()));
        }
        return resultStats;
    }

    private double resultRatio(final Long countMutant, final Long countHuman) {
        double result;
        if (countHuman != null
                && countHuman != 0 && countMutant != null
                && countMutant != 0) {
            result = countMutant.doubleValue() / countHuman.doubleValue();
        } else {
            result = NumberUtils.INTEGER_ZERO;
        }
        return result > NumberUtils.INTEGER_ZERO
                ? Math.round(result * 100.0) / 100.0 : result;
    }

    private boolean isMutant(String... dna) {
        int mutantDna = NumberUtils.INTEGER_ZERO;
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {
                if (j < dna[i].length() - 3) {

                    if (validateConditions.areEqualDna(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2),
                            dna[i].charAt(j + 3))) {
                        mutantDna++;
                    }
                }
                if (i < dna[i].length() - 3) {
                    if (validateConditions.areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j),
                            dna[i + 3].charAt(j))) {
                        mutantDna++;
                    }
                }


                if (j < dna[i].length() - 3 && i < dna[i].length() - 3) {
                    if (validateConditions.areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j + 1),
                            dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
                        mutantDna++;
                    }
                }


                if (dna[i].length() > 3 && j < dna[i].length() - 3 && i > 2) {
                    if (validateConditions.areEqualDna(dna[i].charAt(j), dna[i - 1].charAt(j + 1),
                            dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
                        mutantDna++;
                    }
                }

                if (mutantDna >= 2) {
                    return true;
                }
            }

        }
        return false;
    }

}
