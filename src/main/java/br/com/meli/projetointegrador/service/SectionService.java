package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Category;
import br.com.meli.projetointegrador.model.Section;

import java.util.List;

public interface SectionService {
    Section findById(Long id);
    void updateCurrentSize(Integer amountUsed, Long id);
    List<Batch> checkBatchStockDueDate(Long id, Integer days);
    List<Batch> checkBatchStockDueDateByCategory(Integer days, String category, String order);
}
