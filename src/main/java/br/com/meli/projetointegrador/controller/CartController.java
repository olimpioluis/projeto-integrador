package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.*;
import br.com.meli.projetointegrador.service.AdvertisementService;
import br.com.meli.projetointegrador.service.CartService;
import br.com.meli.projetointegrador.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TotalCartPriceDTO> postPurchaseOrder(@RequestBody CartDTO cartDTO) {
        return new ResponseEntity<>(TotalCartPriceDTO.map(cartService.save(CartDTO.map(cartDTO, customerService, advertisementService))), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CartWithStatusDTO> getOrderProducts(@PathVariable Long orderId) {
        return new ResponseEntity<>(CartWithStatusDTO.map(cartService.findById(orderId)), HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<PurchaseCartDTO> putPurchaseOrder(@PathVariable Long orderId){
        return new ResponseEntity<>(PurchaseCartDTO.map(cartService.updateCartToPurchase(orderId)), HttpStatus.OK);
    }
}
