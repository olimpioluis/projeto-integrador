package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.SectionUnavailableSpaceException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.service.SectionService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SectionAvailableSpace implements Validator{

    private final SectionService sectionService;
    private Long sectionId;
    private final List<Batch> batches;

    @Override
    public void validate() {
        Section section = sectionService.findById(sectionId);
        if(batches.size() > section.getCurrentSize()) throw new SectionUnavailableSpaceException("Section has no available space.");
    }
}
