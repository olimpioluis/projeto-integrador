package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentSectionException;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private SectionRepository sectionRepository;
    @Override
    public Section findById(Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new InexistentSectionException("Section " + id + " does not exists!"));
    }
}
