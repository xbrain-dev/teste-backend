package me.dri.entities.dto;

import java.util.List;

public record SellerResponseDTO(String id, String name, List<SellDTO> sells) {
}
