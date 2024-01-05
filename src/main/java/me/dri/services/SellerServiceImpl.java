package me.dri.services;

import me.dri.entities.Seller;
import me.dri.entities.dto.SellDTO;
import me.dri.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImpl implements  SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public String insertSeller(Seller seller) {
        Seller sellerInserted = this.sellerRepository.insert(seller);
        if (sellerInserted.getId() != null) {
            return sellerInserted.getId();
        }
        return null;
    }

    @Override
    public void sell(SellDTO sellDTO) {


    }

    @Override
    public List<Seller> bestSellersOfWeekend() {
        return null;
    }
}
