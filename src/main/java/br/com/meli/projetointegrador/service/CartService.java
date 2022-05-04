package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Cart;
import br.com.meli.projetointegrador.model.Item;

import java.math.BigDecimal;
import java.util.List;
/**
 * Interface de serviço responsável por processar dados de Cart.
 * @author Igor de Souza Nogueira
 * @author Luis Felipe Floriano Olimpio
 * */
public interface CartService {
    BigDecimal save(Cart cart);
    Cart updateCartToPurchase(Long id);
    Cart findById(Long id);
}
