package me.dri.services;

import me.dri.entities.Sell;
import me.dri.entities.Seller;
import me.dri.entities.dto.SellDTO;
import me.dri.entities.dto.SellResponseDTO;
import me.dri.entities.dto.SellerDTO;
import me.dri.exceptions.FailedSell;
import me.dri.exceptions.NotFoundSellerByName;
import me.dri.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImpl  {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public String insertSeller(SellerDTO sellerDTO) {
        Seller newSeller = new Seller(null, sellerDTO.sellerName(), List.of());
        Seller sellerInserted = this.sellerRepository.insert(newSeller);
        if (sellerInserted.getId() != null) {
            return sellerInserted.getId();
        }
        return null;
    }

    public Seller findSellerByName(String nameSeller) {
        Seller sellerByDatabase = this.sellerRepository.findByName(nameSeller).orElseThrow(() -> new NotFoundSellerByName("Not found user by seller name"));
        return sellerByDatabase;

    }

    public SellResponseDTO toSell(SellDTO sellDTO) {
        Seller sellerByDatabase = this.sellerRepository.findByName(sellDTO.sellerName()).orElseThrow(() -> new NotFoundSellerByName("Not found user by seller name"));
        Sell newSell = new Sell(null, new Date(), sellDTO.value());
        if (!sellerByDatabase.getSells().isEmpty()) {
            sellerByDatabase.getSells().add(newSell);
            this.sellerRepository.save(sellerByDatabase);
            return new SellResponseDTO(sellDTO, sellDTO.sellerName(), sellerByDatabase.getSells().size());

        }
        sellerByDatabase.setSells(List.of(newSell));
        this.sellerRepository.save(sellerByDatabase);
        return new SellResponseDTO(sellDTO, sellDTO.sellerName(), sellerByDatabase.getSells().size());

    }

    public List<Seller> bestSellersOfWeekend() {
        return null;
    }
}
