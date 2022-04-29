package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.InexistentSectionException;
import br.com.meli.projetointegrador.repository.SectionRepository;
import br.com.meli.projetointegrador.service.SectionService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SectionExists implements Validator {

    private final SectionService sectionService;
    private final Long sectionId;

    @Override
    public void validate() {
        sectionService.findById(sectionId);
    }
}
