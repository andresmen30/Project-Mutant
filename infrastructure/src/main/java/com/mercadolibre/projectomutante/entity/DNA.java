package com.mercadolibre.projectomutante.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DNA {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @NotNull
    private String dna;

    private int mutant;


    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public int getMutant() {
        return mutant;
    }

    public void setMutant(int mutant) {
        this.mutant = mutant;
    }


}
