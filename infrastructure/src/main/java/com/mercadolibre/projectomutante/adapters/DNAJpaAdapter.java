package com.mercadolibre.projectomutante.adapters;

import com.mercadolibre.projectomutante.data.DNADto;
import com.mercadolibre.projectomutante.entity.DNA;
import com.mercadolibre.projectomutante.mappers.DNAMapper;
import com.mercadolibre.projectomutante.ports.spi.DNAPersistencePort;
import com.mercadolibre.projectomutante.respository.DNARepository;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class DNAJpaAdapter implements DNAPersistencePort {


    @Autowired
    private DNARepository dnaRepository;

    @Autowired
    public DNAJpaAdapter(final DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }


    @Override
    @Transactional
    public DNADto saveDNA(DNADto dnaDto) {
        DNA dna = DNAMapper.INSTANCE.DNADtoTODNA(dnaDto);
        dnaRepository.save(dna);
        return DNAMapper.INSTANCE.DNAToDNADto(dna);
    }

    @Override
    public List<DNADto> getStats() {
        final List<DNA> listEntity = this.dnaRepository.findAll();
        final List<DNADto> listDto = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listEntity)) {
            listEntity.stream().forEach(entity -> {
                listDto.add(DNAMapper.INSTANCE.DNAToDNADto(entity));
            });
        }

        return listDto;
    }

    @Override
    public boolean recordExist(final DNADto dnaDto) {
        final DNA dna = DNAMapper.INSTANCE.DNADtoTODNA(dnaDto);
        return this.dnaRepository.getByDna(dna.getDna()).isPresent();
    }
}
