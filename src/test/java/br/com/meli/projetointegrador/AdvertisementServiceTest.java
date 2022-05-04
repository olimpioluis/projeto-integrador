package br.com.meli.projetointegrador;

import br.com.meli.projetointegrador.model.Advertisement;
import br.com.meli.projetointegrador.model.Product;
import br.com.meli.projetointegrador.model.Seller;
import br.com.meli.projetointegrador.repository.AdvertisementRepository;
import br.com.meli.projetointegrador.service.AdvertisementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class AdvertisementServiceTest {

    private AdvertisementServiceImpl advertisementService;

    @Mock
    private AdvertisementRepository advertisementRepository;

    @BeforeEach
    private void initializeAdvertisementService() {
        MockitoAnnotations.openMocks(this);
        this.advertisementService = new AdvertisementServiceImpl(advertisementRepository);
    }

    @Test
    public void findByIdTest() {
        Advertisement advertisement = new Advertisement(1L, "Advertisement 1", BigDecimal.valueOf(100), new Product(), new Seller());

        Mockito.when(advertisementRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(advertisement));

         assertEquals("Advertisement 1", advertisementService.findById(1L).getTitle());
    }
}
