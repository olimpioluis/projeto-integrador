package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Advertisement;
/**
 * Interface de serviço responsável por processar  os anuncios.
 * @author Arthur Guedes de Souza
 */
public interface AdvertisementService {
    Advertisement findById(Long id);
}
