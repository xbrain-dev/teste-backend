package me.dri.controllers;


import me.dri.entities.Seller;
import me.dri.entities.dto.SellDTO;
import me.dri.entities.dto.SellResponseDTO;
import me.dri.entities.dto.SellerResponseDTO;
import me.dri.services.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sell")
public class SellController {
    private final SellerServiceImpl service;

    @Autowired
    public SellController(SellerServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<SellResponseDTO> createSell(@RequestBody SellDTO sellDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.toSell(sellDTO));
    }



}
