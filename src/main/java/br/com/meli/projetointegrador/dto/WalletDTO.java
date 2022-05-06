package br.com.meli.projetointegrador.dto;

import br.com.meli.projetointegrador.model.Customer;
import br.com.meli.projetointegrador.model.Wallet;
import br.com.meli.projetointegrador.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class WalletDTO {
    @NotNull(message = "CustomerId missing")
    private Long customerId;
    @NotNull(message = "WalletId missing")
    private String walletNumber;
    private BigDecimal balance;

    public static Wallet map(WalletDTO walletDTO, CustomerService customerService) {
        Customer customer = customerService.findById(walletDTO.getCustomerId());

        return Wallet.builder().customer(customer).walletNumber(walletDTO.getWalletNumber()).balance(BigDecimal.ZERO).build();
    }

    public static WalletDTO map(Wallet wallet) {
        return new WalletDTO(wallet.getCustomer().getId(), wallet.getWalletNumber(), wallet.getBalance());
    }
}
