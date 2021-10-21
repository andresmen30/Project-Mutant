package com.mercadolibre.projectomutante.respository;

import com.mercadolibre.projectomutante.entity.DNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DNARepository extends JpaRepository<DNA, Long> {

    @Query(value = "select d from DNA d where d.dna=:dna")
    Optional<DNA> getByDna(@Param("dna") final String dna);
}
