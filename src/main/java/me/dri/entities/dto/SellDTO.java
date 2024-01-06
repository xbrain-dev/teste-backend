package me.dri.entities.dto;

import java.util.Date;

public record SellDTO(Date date, Double value, String sellerName) {
}
