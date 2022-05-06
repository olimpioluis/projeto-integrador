package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.model.Wallet;

import java.math.BigDecimal;

public interface WalletService {
    Wallet save(Wallet wallet);
    Wallet findById(Long id);
    Wallet deposit(Long id, BigDecimal amount);
    Wallet withdraw(Long id, BigDecimal amount);
    Wallet getWallet(Long id);
    Wallet findByUserId(Long customerId);
}
