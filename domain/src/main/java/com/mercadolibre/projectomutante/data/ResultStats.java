package com.mercadolibre.projectomutante.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ResultStats {

    private long countMutantDna;
    private long countHumantDna;
    private double ratio;

}
