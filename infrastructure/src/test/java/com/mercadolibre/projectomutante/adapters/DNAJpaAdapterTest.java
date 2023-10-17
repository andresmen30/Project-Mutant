package com.mercadolibre.projectomutante.adapters;

import com.mercadolibre.projectomutante.entity.DNA;
import com.mercadolibre.projectomutante.respository.DNARepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class DNAJpaAdapterTest {

    private DNARepository dnaRepository = Mockito.mock(DNARepository.class);
    private DNAJpaAdapter dnaJpaAdapter;


    @BeforeEach
    void setUp() {
        Mockito.when(dnaRepository.findAll()).thenReturn(getList());
    }

    @Test
    void getStats() {
        dnaJpaAdapter = new DNAJpaAdapter(dnaRepository);
        Assertions.assertTrue(CollectionUtils.isNotEmpty(dnaJpaAdapter.getStats()));

    }

    private List<DNA> getList() {
        final String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        final List<DNA> list = new ArrayList<>();
        list.add(new DNA(1L, dna.toString(), 0));
        list.add(new DNA(1L, dna.toString(), 1));
        list.add(new DNA(1L, dna.toString(), 1));
        list.add(new DNA(1L, dna.toString(), 0));
        return list;
    }


}