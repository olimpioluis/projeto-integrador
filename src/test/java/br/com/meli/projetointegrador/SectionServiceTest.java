package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Category;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.repository.SectionRepository;
import br.com.meli.projetointegrador.service.SectionService;
import br.com.meli.projetointegrador.service.SectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

public class SectionServiceTest {

    private SectionService sectionService;

    @Mock
    private SectionRepository sectionRepository;

    @BeforeEach
    private void initializeSectionService() {
        MockitoAnnotations.openMocks(this);
        this.sectionService = new SectionServiceImpl(sectionRepository);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(sectionRepository.findById(1L))
                .thenReturn(java.util.Optional.of(new Section(1L, "Section 1", Category.FRESH, 10, 10, new Warehouse(), Arrays.asList(new Batch()))));
            Section section = sectionRepository.findById(1L).orElse(new Section());

        assertEquals("Section 1", section.getName());
    }
}
