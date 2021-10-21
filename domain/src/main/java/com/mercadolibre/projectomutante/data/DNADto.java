package com.mercadolibre.projectomutante.data;


import lombok.*;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
public class DNADto {

    @NotEmpty(message = "Oops el sujeto de pruebas debe llevar consigo su ADN")
    private String[] dna;
    private int mutant;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }


    public int getMutant() {
        return mutant;
    }

    public void setMutant(int mutant) {
        this.mutant = mutant;
    }


}
