package br.com.meli.projetointegrador.validator;


import br.com.meli.projetointegrador.exception.SectionNotMatchWithWarehouseException;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.service.SectionService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SectionMatchWithWarehouse implements Validator{

    private Section section;
    private SectionService sectionService;

    @Override
    public void validate() {

        Section correctSection = sectionService.findById(section.getId());

        if(section.getWarehouse().getId() != correctSection.getWarehouse().getId()) throw new SectionNotMatchWithWarehouseException("The informed Section does not match with informed Warehouse!");

    }
}
