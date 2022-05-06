package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Payment;

public interface PaymentService {
    Payment save(Payment payment);
    Payment pay(Long id);
    Payment findById(Long id);
    Payment getPayment(Long id);
}
