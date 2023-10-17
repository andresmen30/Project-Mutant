package com.mercadolibre.projectomutante.ports.spi;

import com.mercadolibre.projectomutante.data.DNADto;

import java.util.List;

public interface DNAPersistencePort {

    DNADto saveDNA(final DNADto dnaDto);

    List<DNADto> getStats();

    boolean recordExist(final DNADto dnaDto);


}
