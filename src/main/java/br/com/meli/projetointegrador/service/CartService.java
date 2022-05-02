package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Cart;
import br.com.meli.projetointegrador.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    BigDecimal save(Cart cart);
    Cart updateCartToPurchase(Long id);
    Cart findById(Long id);
}
