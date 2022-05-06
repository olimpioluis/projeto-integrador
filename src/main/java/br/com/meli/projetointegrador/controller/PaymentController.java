package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.PaymentDTO;
import br.com.meli.projetointegrador.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fresh-products/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{paymentId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long paymentId) {
        return new ResponseEntity<>(PaymentDTO.map(paymentService.getPayment(paymentId)), HttpStatus.OK);
    }

    @PutMapping("/{paymentId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<PaymentDTO> pay(@PathVariable Long paymentId) {
        return new ResponseEntity<>(PaymentDTO.map(paymentService.pay(paymentId)), HttpStatus.OK);
    }
}
