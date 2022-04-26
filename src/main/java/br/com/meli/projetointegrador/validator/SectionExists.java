package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.InexistentSectionException;
import br.com.meli.projetointegrador.repository.SectionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SectionExists implements Validator {

    private final SectionRepository sectionRepository;
    private final Long sectionId;

    @Override
    public void validate() {
        if (sectionRepository.findById(sectionId).isEmpty()) {
            throw new InexistentSectionException("Section " + sectionId + " does not exists!");
        }
    }
}
