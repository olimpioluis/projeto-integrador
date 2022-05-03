package br.com.meli.projetointegrador.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
class ExampleController {

    @GetMapping("/generic")
    public String allAccess() {
        return "Public Content.";
    }


    @GetMapping("/customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public String customerAccess() {
        return "Customer Board.";
    }


    @GetMapping("/stock")
    @PreAuthorize("hasRole('ROLE_STOCK_MANAGER')")
    public String stockAccess() {
        return "Stock Manager Board.";
    }

    @GetMapping("/seller")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public String sellerAccess() {
        return "Seller Board.";
    }
}