package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentWalletException;
import br.com.meli.projetointegrador.model.Wallet;
import br.com.meli.projetointegrador.repository.WalletRepository;
import br.com.meli.projetointegrador.validator.BalanceHasValidAmount;
import br.com.meli.projetointegrador.validator.UserMatchsTokenSent;
import br.com.meli.projetointegrador.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    private CustomerService customerService;

    @Override
    public Wallet save(Wallet wallet) {
        new UserMatchsTokenSent(wallet.getCustomer().getId(), customerService).validate();

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new InexistentWalletException("Wallet " + id + " does not exists!"));
    }

    @Override
    public Wallet deposit(Long id, BigDecimal amount) {
        Wallet wallet = findById(id);

        new UserMatchsTokenSent(wallet.getCustomer().getId(), customerService).validate();

        wallet.setBalance(wallet.getBalance().add(amount));

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet withdraw(Long id, BigDecimal amount) {
        Wallet wallet = findById(id);

        List<Validator> validators = Arrays.asList(
                new BalanceHasValidAmount(wallet.getBalance().subtract(amount), "The value requested to withdraw can not be obtained"),
                new UserMatchsTokenSent(wallet.getCustomer().getId(), customerService)
        );

        validators.forEach(Validator::validate);

        wallet.setBalance(wallet.getBalance().subtract(amount));

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWallet(Long id) {
        Wallet wallet = findById(id);

        new UserMatchsTokenSent(wallet.getCustomer().getId(), customerService);

        return wallet;
    }

    @Override
    public Wallet findByUserId(Long customerId) {
        return walletRepository.findByCustomerId(customerId).orElseThrow(() -> new InexistentWalletException("Wallet with user id " + customerId + " does not exists!"));
    }
}
