package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentSectionException;
import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.Category;
import br.com.meli.projetointegrador.model.Section;
import br.com.meli.projetointegrador.model.Warehouse;
import br.com.meli.projetointegrador.repository.SectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.Optional;

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
        Mockito.when(sectionRepository.findById(Mockito.any()))
                .thenReturn(java.util.Optional.of(new Section(1L, "Section 1", Category.FRESH, 10, 10, new Warehouse(), Collections.singletonList(new Batch()))));

        assertEquals("Section 1", sectionService.findById(1L).getName());
    }

    @Test
    public void inexistentSectionException(){
        Mockito.when(sectionRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        assertThrows(InexistentSectionException.class, () -> sectionService.findById(1L));
    }

    @Test
    public void updateCurrentSizeTest(){
        Section section = new Section(1L, "Section 1", Category.FRESH, 10, 10, new Warehouse(), Collections.singletonList(new Batch()));

        Mockito.when(sectionRepository.findById(Mockito.any()))
                .thenReturn(java.util.Optional.of(section));
        Mockito.when(sectionRepository.save(Mockito.any()))
                .thenReturn(section);

        sectionService.updateCurrentSize(2, 1L);

        assertEquals(8, section.getCurrentSize());

    }


}
