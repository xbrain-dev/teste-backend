package me.dri.services;

import me.dri.entities.Seller;
import me.dri.entities.dto.SellDTO;

import java.util.Date;
import java.util.List;

public interface SellerService {

    String insertSeller(Seller seller);

    void sell(SellDTO sellDTO);

    List<Seller> bestSellersOfWeekend();
}
