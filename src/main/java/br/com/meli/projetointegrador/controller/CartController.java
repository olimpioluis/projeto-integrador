package br.com.meli.projetointegrador.controller;

import br.com.meli.projetointegrador.dto.CartDTO;
import br.com.meli.projetointegrador.dto.CartProductDTO;
import br.com.meli.projetointegrador.dto.TotalCartPriceDTO;
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
    public ResponseEntity<List<CartProductDTO>> getOrderProducts(@PathVariable Long orderId) {
        return new ResponseEntity<>(CartProductDTO.map(cartService.getOrderProducts(orderId)), HttpStatus.OK);
    }
}
