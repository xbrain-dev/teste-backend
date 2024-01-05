package me.dri.TesteBackEnd.unitest;

import me.dri.entities.Sell;
import me.dri.entities.Seller;
import me.dri.entities.dto.SellDTO;
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
        Seller sellerInsert = new Seller(null, "Diego");
        Seller sellerInserted = new Seller("id123", "Diego");

        when(this.repository.insert(sellerInsert)).thenReturn(sellerInserted);
        var resultInsert = this.service.insertSeller(sellerInsert);

        assertNotNull(resultInsert);
        assertEquals(sellerInserted.getId(), resultInsert);
    }

    @Test
    void testSell() {
        Date dateNow = new Date();
        Double valueOfSell = 200.0;
        Seller seller = new Seller("id123", "Diego");
        SellDTO sellDTO = new SellDTO(dateNow, valueOfSell, seller.getName());
        Sell sellInsert = new Sell(null, sellDTO.date(), sellDTO.value(), seller);
        when(this.repository.findByName(seller.getName())).thenReturn(Optional.of(seller));
        seller.setSells(List.of(sellInsert));
        when(this.repository.insert(seller)).thenReturn(seller);
        this.service.sell(sellDTO);
    }
}
