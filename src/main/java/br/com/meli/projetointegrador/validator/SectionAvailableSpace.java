package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.SectionUnavailableSpaceException;
import br.com.meli.projetointegrador.model.InboundOrder;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.repository.SectionRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SectionAvailableSpace implements Validator{

    private final SectionRepository sectionRepository;
    private Long sectionId;
    private final InboundOrder inboundOrder;

    @Override
    public void validate() {
        Section section = sectionRepository.findById(sectionId).orElse(new Section());
        if(inboundOrder.getBatchList().size() > section.getSize()) throw new SectionUnavailableSpaceException("Section has no available space.");
    }
}
