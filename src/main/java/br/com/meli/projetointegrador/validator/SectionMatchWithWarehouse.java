package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.InexistentSectionException;
import br.com.meli.projetointegrador.exception.SectionNotMatchWithWarehouseException;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.repository.SectionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SectionMatchWithWarehouse implements Validator{

    private Section section;
    private SectionRepository sectionRepository;

    @Override
    public void validate() {

        Section correctSection = sectionRepository.findById(section.getId()).orElseThrow(() ->new InexistentSectionException("Section " + section.getId() + " does not exists!"));

        if(section.getWarehouse().getId() != correctSection.getWarehouse().getId()) throw new SectionNotMatchWithWarehouseException("The informed Section does not match with informed Warehouse!");
    }
}
