package me.dri.TesteBackEnd.unitest;

import me.dri.entities.Sell;
import me.dri.entities.Seller;
import me.dri.entities.dto.SellDTO;
import me.dri.entities.dto.SellerDTO;
import me.dri.exceptions.NotFoundSellerByName;
import me.dri.repositories.SellerRepository;
import me.dri.services.SellerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class SellerServicesTest {

    @Mock
    SellerRepository repository;

    @InjectMocks
    SellerServiceImpl service;


    public SellerServicesTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testInsertDocument() {
        SellerDTO sellerDTO = new SellerDTO("Diego");
        Seller sellerInsert = new Seller("123", "Diego");
        Seller sellerInserted = new Seller("id123", "Diego");

        when(this.repository.insert(sellerInsert)).thenReturn(sellerInserted);
        var resultInsert = this.service.insertSeller(sellerDTO);

        assertNotNull(resultInsert);
        assertEquals(sellerInserted.getId(), resultInsert);
    }

    @Test
    void testSell() {
        Date dateNow = new Date();
        Double valueOfSell = 200.0;
        Seller seller = new Seller("id123", "Diego");
        SellerDTO sellerTest = new SellerDTO("diego");
        SellDTO sellDTO = new SellDTO(dateNow, valueOfSell, sellerTest.sellerName());
        Sell sellInsert = new Sell(null, sellDTO.date(), sellDTO.value());
        when(this.repository.findByName(seller.getName())).thenReturn(Optional.of(seller));
        seller.setSells(List.of(sellInsert));
        when(this.repository.insert(seller)).thenReturn(seller);
        this.service.toSell(sellDTO);
    }

    @Test
    void testExceptionNotFoundSellerInSellMethod() {
        SellerDTO sellerTest = new SellerDTO("diego");
        SellDTO sellDTO = new SellDTO(new Date(), 200.0, sellerTest.sellerName());
        when(this.repository.findByName(sellerTest.sellerName())).thenReturn(Optional.empty());
        assertThrows(NotFoundSellerByName.class, () -> this.service.toSell(sellDTO));
    }
}
