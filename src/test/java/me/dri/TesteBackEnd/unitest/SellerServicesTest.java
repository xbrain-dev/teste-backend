package me.dri.TesteBackEnd.unitest;

import me.dri.entities.Sell;
import me.dri.entities.Seller;
import me.dri.entities.dto.SellDTO;
import me.dri.entities.dto.SellerDTO;
import me.dri.exceptions.FailedInsertSellerOnDB;
import me.dri.exceptions.NotFoundSellerByName;
import me.dri.repositories.SellerRepository;
import me.dri.services.SellerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class SellerServicesTest {

    @Mock
    SellerRepository repository;

    @InjectMocks
    SellerServiceImpl service;


    public SellerServicesTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testInsertSeller() {
        SellerDTO mockSellerDTO = mock(SellerDTO.class);
        Seller mockSeller = mock(Seller.class);
        when(mockSeller.getId()).thenReturn("someId");
        when(this.repository.insert(any(Seller.class))).thenReturn(mockSeller); // Return the mockSeller

        var resultInsertedSeller = this.service.insertSeller(mockSellerDTO);

        assertNotNull(resultInsertedSeller);
        assertEquals("someId", resultInsertedSeller.id());
        assertEquals(0, resultInsertedSeller.sells().size()); // Check if array of sells starts with 0 sells
    }

    @Test
    void testFindSellerByName() {
        Seller mockSeller = mock(Seller.class);
        when(mockSeller.getName()).thenReturn("Diego");
        when(this.repository.findByName(mockSeller.getName())).thenReturn(Optional.of(mockSeller));
        when(mockSeller.getId()).thenReturn("someId");
        when(mockSeller.getSells()).thenReturn(List.of(mock(Sell.class)));
        var resultSellerResponseDTO = this.service.findSellerByName(mockSeller.getName());
        assertEquals("someId", resultSellerResponseDTO.id());
        assertEquals("Diego", resultSellerResponseDTO.name());
        assertEquals(1, resultSellerResponseDTO.sells().size());
    }

    @Test
    void testNotFoundSellerByNameException() {
        Seller mockSeller = mock(Seller.class);
        when(mockSeller.getName()).thenReturn("Diego");
        when(this.repository.findByName(mockSeller.getName())).thenReturn(Optional.empty()); // This is when DB return null
        assertThrows(NotFoundSellerByName.class, () -> this.service.findSellerByName(mockSeller.getName()));
    }

    @Test
    void testFailedExceptionInsertSeller() {
        SellerDTO mockSellerDTO = mock(SellerDTO.class);
        Seller mockSeller = mock(Seller.class);
        when(mockSeller.getId()).thenReturn(null);
        when(this.repository.insert(any(Seller.class))).thenReturn(mockSeller);
        assertThrows(FailedInsertSellerOnDB.class, () -> this.service.insertSeller(mockSellerDTO)); // Return the exception because that getId is null from DB
    }

    @Test
    void testToSell() {
        Date mockDate = new Date();
        SellDTO mockSellDTO = mock(SellDTO.class);
        when(mockSellDTO.sellerName()).thenReturn("Diego");
        when(mockSellDTO.date()).thenReturn(mockDate);
        when(mockSellDTO.value()).thenReturn(200.0);
        Seller mockSeller = mock(Seller.class);
        when(mockSeller.getName()).thenReturn("Diego");
        when(this.repository.findByName(mockSeller.getName())).thenReturn(Optional.of(mockSeller));
        when(mockSeller.getSells()).thenReturn(List.of(mock(Sell.class))); // First sell of the seller
        var resultSellResponseDTO = this.service.toSell(mockSellDTO);
        assertEquals("Diego", resultSellResponseDTO.sellDto().sellerName());
        assertEquals(mockDate, resultSellResponseDTO.sellDto().date());
        assertEquals(200.0, resultSellResponseDTO.sellDto().value());
        assertEquals(1, resultSellResponseDTO.amountSells());
    }

    @Test
    void testBestSellersOfWeekend() {
        List<Seller> sellers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Seller seller = new Seller("id" + i, "Diego" + i);
            sellers.add(seller);
        }
        for (int j = 0; j < 20; j++) {
            double value = 0.0;
            if (sellers.get(j).getName().equals("Diego1")) {
                value = 400.0;
            } else {
                value = 100.0;
            }
            Sell sell = new Sell(new Date(), value + 1, sellers.get(j));
            sellers.get(j).addSell(sell);
        }
        when(this.repository.findAll()).thenReturn(sellers);
        var resultBest10 = this.service.bestSellersWeekend();
        assertEquals(10, resultBest10.size());
        assertTrue(resultBest10.stream().anyMatch(dto -> dto.seller().equals("Diego1")));
        assertTrue(resultBest10.stream().anyMatch(dto -> dto.seller().equals("Diego2")));
    }



}
