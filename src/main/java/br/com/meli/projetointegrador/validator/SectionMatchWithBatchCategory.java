package br.com.meli.projetointegrador.validator;

import br.com.meli.projetointegrador.exception.SectionMatchWithBatchCategoryException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SectionMatchWithBatchCategory implements Validator {

    private final Batch batch;

    @Override
    public void validate() {
        if (batch.getMinTemperature() < 0 && !batch.getSection().getCategory().name().equals(Category.FROZEN.name())) {
            throw new SectionMatchWithBatchCategoryException("Batch does not match  this section!");
        } else if (batch.getMinTemperature() < 10 && !batch.getSection().getCategory().name().equals(Category.REFRIGERATED.name())) {
            throw new SectionMatchWithBatchCategoryException("Batch does not match  this section!");
        } else if (batch.getMinTemperature() >= 10 && !batch.getSection().getCategory().name().equals(Category.FRESH.name())) {
            throw new SectionMatchWithBatchCategoryException("Batch does not match  this section!");
        }
    }
}



