package me.dri.controllers;


import me.dri.entities.Seller;
import me.dri.entities.dto.SellerDTO;
import me.dri.entities.dto.SellerResponseDTO;
import me.dri.services.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {
    private final SellerServiceImpl service;

    @Autowired
    public SellerController(SellerServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    ResponseEntity<SellerResponseDTO> create(@RequestBody SellerDTO sellerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.insertSeller(sellerDTO));
    }
    @GetMapping("/{sellerName}")
    ResponseEntity<SellerResponseDTO> findSellerByName(@PathVariable String sellerName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.findSellerByName(sellerName));
    }

}
