package me.dri.services;

import me.dri.entities.Sell;
import me.dri.entities.Seller;
import me.dri.entities.dto.*;
import me.dri.exceptions.FailedInsertSellerOnDB;
import me.dri.exceptions.NotFoundSellerByName;
import me.dri.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl  {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public SellerResponseDTO insertSeller(SellerDTO sellerDTO) {
        Seller newSeller = new Seller(null, sellerDTO.sellerName(), List.of());
        Seller sellerInserted = this.sellerRepository.insert(newSeller);
        if (sellerInserted.getId() != null) {
            return new SellerResponseDTO(sellerInserted.getId(), sellerInserted.getName(), this.convertListSellsToSellsDTO(sellerInserted.getSells(), sellerInserted.getName()));
        }
        throw new FailedInsertSellerOnDB("Failed to insert seller on database");
    }

    public SellerResponseDTO findSellerByName(String nameSeller) {
        Seller seller = this.sellerRepository.findByName(nameSeller).orElseThrow(() -> new NotFoundSellerByName("Not found user by seller name"));
        return new SellerResponseDTO(seller.getId(), seller.getName(), this.convertListSellsToSellsDTO(seller.getSells(), seller.getName()));
    }

    public SellResponseDTO toSell(SellDTO sellDTO) {
        Seller sellerByDatabase = this.sellerRepository.findByName(sellDTO.sellerName()).orElseThrow(() -> new NotFoundSellerByName("Not found user by seller name"));
        Sell newSell = new Sell(sellDTO.date(), sellDTO.value(), sellerByDatabase);
        sellerByDatabase.addSell(newSell);
        this.sellerRepository.save(sellerByDatabase);
        return new SellResponseDTO(sellDTO, sellerByDatabase.getSells().size());
    }

    public List<BestSellersDTO> bestSellersWeekend() {
        LocalDate currentDate = LocalDate.now();
        Date weekStart = Date.from(currentDate.minusDays(currentDate.getDayOfWeek().getValue() - 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date weekEnd = Date.from(currentDate.plusDays(7 - currentDate.getDayOfWeek().getValue()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Seller> allSellers = this.sellerRepository.findAll();
        Map<String, Double> dailySalesAverages = new HashMap<>();
        this.setSalesAverage(allSellers, dailySalesAverages);
        return this.getTop10SalesWeekend(dailySalesAverages);
    }

    private List<SellDTO> convertListSellsToSellsDTO(List<Sell>  fromSells, String  sellerName) {
        return fromSells.stream().map(s -> new SellDTO(s.getDate(), s.getValue(), sellerName)).collect(Collectors.toList());
    }

    private void setSalesAverage(List<Seller> sellers, Map<String, Double> dailySalesAverages) {

        for (Seller seller : sellers) {
            Map<LocalDate, List<Sell>> salesByDate = seller.getSells().stream()
                    .collect(Collectors.groupingBy(
                            sell -> sell.getDate().toInstant()
                                    .atZone(ZoneId.systemDefault()).toLocalDate()
                    ));

            double totalSales = salesByDate.values().stream().mapToDouble(List::size).sum();
            double daysWithData = salesByDate.size();
            double dailyAverage = totalSales / daysWithData;

            dailySalesAverages.put(seller.getName(), dailyAverage);
        }
    }

    private List<BestSellersDTO> getTop10SalesWeekend(Map<String, Double> dailySalesAverages) {
        return dailySalesAverages.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .map(entry -> new BestSellersDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
