package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentAdvertisementException;
import br.com.meli.projetointegrador.model.Advertisement;
import br.com.meli.projetointegrador.repository.AdvertisementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
/**
 * Classe de serviço responsável por processar  os anuncios.
 * @author Arthur Guedes de Souza
 */
@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    @Override
    public Advertisement findById(Long id) {
        return advertisementRepository.findById(id).orElseThrow(() -> new InexistentAdvertisementException("Advertisement " + id + " does not exists!"));
    }
}
