package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.model.Advertisement;
import br.com.meli.projetointegrador.model.Cart;
import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.repository.ItemRepository;
import br.com.meli.projetointegrador.service.ItemService;
import br.com.meli.projetointegrador.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    @BeforeEach
    private void initializeItemService() {
        MockitoAnnotations.openMocks(this);
        this.itemService = new ItemServiceImpl(itemRepository);
    }

    private List<Item> generateItem() {
        return Arrays.asList(
                new Item(1L, new Advertisement(), new Cart(), 3),
                new Item(1L, new Advertisement(), new Cart(), 3));
    }

    @Test
    public void saveTest() {
        List<Item> items = generateItem();
        Mockito.when(itemRepository.saveAll(Mockito.any())).thenReturn(items);
        assertEquals(items.get(0), itemService.save(items).get(0));
    }


}
