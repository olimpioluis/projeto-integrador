package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Batch;
import br.com.meli.projetointegrador.model.InboundOrder;

import java.util.List;
/**
 * Interface de serviço responsável por processar dados de InboundOrder.
 * @author Jeferson Barbosa Souza
 * */
public interface InboundOrderService {
    List<Batch> save(InboundOrder inboundOrder);
    List<Batch> update(InboundOrder inboundOrder);
    InboundOrder findById(Long id);
}
