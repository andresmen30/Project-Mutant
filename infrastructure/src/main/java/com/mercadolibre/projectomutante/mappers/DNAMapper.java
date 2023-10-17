package com.mercadolibre.projectomutante.mappers;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.entity.DNA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

@Mapper
public interface DNAMapper {

    DNAMapper INSTANCE = Mappers.getMapper(DNAMapper.class);

    @Mapping(target = "dna", expression = "java(getArrayFromString(dna.getDna()))")
    DNADto DNAToDNADto(final DNA dna);

    @Mapping(target = "dna", expression = "java(getStringFromArray(dnaDto.getDna()))")
    @Mapping(target = "mutant", expression = "java(dnaDto.getMutant())")
    DNA DNADtoTODNA(final DNADto dnaDto);

    default String[] getArrayFromString(final String dna) {
        return dna.split(",");
    }

    default String getStringFromArray(final String... dna) {
        final StringBuilder builder = new StringBuilder();
        Arrays.stream(dna).forEach(dnaString -> {
            builder.append(dnaString).append(",");
        });
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }


}
