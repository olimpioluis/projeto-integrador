package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.InexistentSectionException;
import br.com.meli.projetointegrador.model.Sector;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SectionExists implements  Validator {

    private final SectionRepository sectionRepository;
    private final String sectionCode;

    @Override
    public void validate() {
        Sector section = sectionRepository.findById(sectionCode);
        if (section.getId() == null) {
            throw new InexistentSectionException("The informed Section does not exists!");
        }
    }
}
