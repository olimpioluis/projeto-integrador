package br.com.meli.projetointegrador.service;


import br.com.meli.projetointegrador.model.OrderStatus;
import br.com.meli.projetointegrador.repository.OrderStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }
}
