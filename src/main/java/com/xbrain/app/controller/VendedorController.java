package com.xbrain.app.controller;

import com.xbrain.app.dto.VendedorDTO;
import com.xbrain.app.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorController {

    private final VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<?> getAll(VendedorDTO vendedorDTO, Pageable pageable) {
        return ResponseEntity.ok(vendedorService.getAll(vendedorDTO, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getVById(@PathVariable Long id){
        return ResponseEntity.ok(vendedorService.findById(id));
    }

    // TODO - Descomentar após a conclusão da logica do ranking;

//    @GetMapping("/ranking")
//    public ResponseEntity<?> getRanking(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
//                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
//        return ResponseEntity.ok(vendedorService.ranking(fromDate, toDate));
//    }

    @PostMapping
    public ResponseEntity<?> addV(@RequestBody @Valid VendedorDTO vendedorDTO) {
        return ResponseEntity.ok(vendedorService.saveVendedor(vendedorDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updV(@PathVariable Long id,
                                  @RequestBody @Valid VendedorDTO vendedorDTO){
        return ResponseEntity.ok(vendedorService.updateVendedor(id, vendedorDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delV(@PathVariable Long id) {
        return ResponseEntity.ok(vendedorService.deleteVendedor(id));
    }

}
