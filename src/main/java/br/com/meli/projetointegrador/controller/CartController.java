package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.*;
import br.com.meli.projetointegrador.service.AdvertisementService;
import br.com.meli.projetointegrador.service.CartService;
import br.com.meli.projetointegrador.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * Classe controladora responsável por lidar com as rotas referentes a classe Cart.
 * Possui rotas para criar ordens de compra, edita-las e efetiva-las.
 * @author Igor de Souza Nogueira
 * @author Luis Felipe Floriano Olimpio
 * */
@RestController
@RequestMapping("/api/v1/fresh-products/orders")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<TotalCartPriceDTO> postPurchaseOrder(@RequestBody CartDTO cartDTO) {
        return new ResponseEntity<>(TotalCartPriceDTO.map(cartService.save(CartDTO.map(cartDTO, customerService, advertisementService))), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CartWithStatusDTO> getOrderProducts(@PathVariable Long orderId) {
        return new ResponseEntity<>(CartWithStatusDTO.map(cartService.findById(orderId)), HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<PurchaseCartDTO> putPurchaseOrder(@PathVariable Long orderId){
        return new ResponseEntity<>(PurchaseCartDTO.map(cartService.updateCartToPurchase(orderId)), HttpStatus.OK);
    }
}
