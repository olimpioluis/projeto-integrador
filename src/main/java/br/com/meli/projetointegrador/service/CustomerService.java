package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Customer;

public interface CustomerService {
    Customer findById(Long id);
    Customer findByUserUsername(String username);
    Customer findCustomerByUser_Id(Long userId);
}
