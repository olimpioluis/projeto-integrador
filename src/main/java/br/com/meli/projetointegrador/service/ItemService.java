package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> save(List<Item> items);
}
