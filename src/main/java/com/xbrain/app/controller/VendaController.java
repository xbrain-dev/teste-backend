package com.xbrain.app.controller;

import com.xbrain.app.dto.VendaDTO;
import com.xbrain.app.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vendas")
public class VendaController {

    private final VendaService vendaService;

    @GetMapping
    public ResponseEntity<?> getAll(VendaDTO vendaDTO, Pageable pageable) {
        return ResponseEntity.ok(vendaService.getAll(vendaDTO, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getVendaById(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> addVenda(@RequestBody VendaDTO vendaDTO) {
        return ResponseEntity.ok(vendaService.save(vendaDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updVenda(@PathVariable Long id, @RequestBody VendaDTO vendaDTO) {
        return ResponseEntity.ok(vendaService.update(id, vendaDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delVenda(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.delete(id));
    }
}