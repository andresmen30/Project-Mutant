package com.mercadolibre.projectomutante.adapters;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.entity.DNA;
import com.mercadolibre.projectomutante.respository.DNARepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class DNAJpaAdapterRecordExistTest {

    private DNARepository dnaRepository = Mockito.mock(DNARepository.class);
    private DNAJpaAdapter dnaJpaAdapter;

    @BeforeEach
    void setUp() {
        Mockito.when(dnaRepository.getByDna("TTTTT,CCCCC")).thenReturn(getDna());
    }

    @Test
    void recordExist() {
        dnaJpaAdapter = new DNAJpaAdapter(dnaRepository);
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        Assertions.assertFalse(dnaJpaAdapter.recordExist(new DNADto(dna, 1)));
    }

    private Optional<DNA> getDna() {
        return Optional.of(new DNA(1L, "TTTTT,CCCCC", 1));
    }
}