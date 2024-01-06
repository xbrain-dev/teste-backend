package me.dri.entities.dto;

public record BestSellersDTO(SellerDTO seller, Integer amountSells, Double averageSellsDaily) {
}
