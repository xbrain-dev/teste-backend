package me.dri.entities.dto;

public record SellResponseDTO(SellDTO sellDto, String sellerName, Integer amountSells) {
}
