package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Section;

public interface SectionService {
    Section findById(Long id);
    void updateCurrentSize(Integer amount, Long sectionId, boolean operation);
}
