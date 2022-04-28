package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Override
    public List<Item> save(List<Item> items) {
        return itemRepository.saveAll(items);
    }
}
