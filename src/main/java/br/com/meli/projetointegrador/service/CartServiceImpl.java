package br.com.meli.projetointegrador.service;

import br.com.meli.projetointegrador.exception.InexistentCartException;
import br.com.meli.projetointegrador.model.Cart;
import br.com.meli.projetointegrador.model.Item;
import br.com.meli.projetointegrador.repository.CartRepository;
import br.com.meli.projetointegrador.validator.ProductExpirationDateGreaterThan3Weeks;
import br.com.meli.projetointegrador.validator.ProductHasEnoughStock;
import br.com.meli.projetointegrador.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ProductService productService;
    private BatchService batchService;
    private ItemService itemService;
    private CustomerService customerService;
    private OrderStatusService orderStatusService;

    @Override
    public BigDecimal save(Cart cart) {
        List<Validator> validators = Arrays.asList(
                new ProductExpirationDateGreaterThan3Weeks(cart.getItems(), batchService),
                new ProductHasEnoughStock(productService, cart.getItems())
        );

        validators.forEach(Validator::validate);

        cart.setTotalCart(cart.getItems().stream().reduce(BigDecimal.valueOf(0), (acc, nextItem) -> acc.add(BigDecimal.valueOf(nextItem.getQuantity()).multiply(nextItem.getAdvertisement().getPrice())), BigDecimal::add));

        orderStatusService.save(cart.getOrderStatus());

        Cart cartCreated = cartRepository.save(cart);

        cart.getItems().forEach(item -> item.setCart(cartCreated));

        itemService.save(cart.getItems());

        return cart.getTotalCart();
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new InexistentCartException("Cart " + id + " does not exists!"));
    }

    @Override
    public List<Item> getOrderProducts(Long id) {
        return findById(id).getItems();
    }
}
