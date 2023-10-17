package com.mercadolibre.projectomutante.ports.api;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.data.ResultStats;

public interface DNAServicePort {

    DNADto saveDNA(final DNADto dnaDto);

    ResultStats getStats();

    boolean isExist(final DNADto dnaDto);
}
