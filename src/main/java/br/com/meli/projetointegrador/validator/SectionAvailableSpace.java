package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.SectionUnavailableSpaceException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.repository.SectionRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SectionAvailableSpace implements Validator{

    private final SectionRepository sectionRepository;
    private Long sectionId;
    private final List<Batch> batches;

    @Override
    public void validate() {
        Section section = sectionRepository.findById(sectionId).orElse(new Section());
        if(batches.size() > section.getCurrentSize()) throw new SectionUnavailableSpaceException("Section has no available space.");
    }
}
