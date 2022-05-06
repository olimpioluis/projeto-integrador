package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.WalletDTO;
import br.com.meli.projetointegrador.dto.WalletTransactionDTO;
import br.com.meli.projetointegrador.service.CustomerService;
import br.com.meli.projetointegrador.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<WalletDTO> postAccount(@RequestBody WalletDTO walletDTO) {
        return new ResponseEntity<>(WalletDTO.map(walletService.save(WalletDTO.map(walletDTO, customerService))), HttpStatus.CREATED);
    }

    @PutMapping("/deposit/{walletId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<WalletDTO> depositInAccount(@PathVariable Long walletId, @RequestBody WalletTransactionDTO walletTransactionDTO) {
        return new ResponseEntity<>(WalletDTO.map(walletService.deposit(walletId, walletTransactionDTO.getAmount())), HttpStatus.OK);
    }

    @PutMapping("/withdraw/{walletId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<WalletDTO> withdrawFromAccount(@PathVariable Long walletId, @RequestBody WalletTransactionDTO walletTransactionDTO) {
        return new ResponseEntity<>(WalletDTO.map(walletService.withdraw(walletId, walletTransactionDTO.getAmount())), HttpStatus.OK);
    }

    @GetMapping("/{walletId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<WalletDTO> getAccount(@PathVariable Long walletId) {
        return new ResponseEntity<>(WalletDTO.map(walletService.getWallet(walletId)), HttpStatus.OK);
    }
}
