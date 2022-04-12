package com.xbrain.app.controller;

import com.xbrain.app.dto.VendedorDTO;
import com.xbrain.app.service.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

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

    // TODO - Quando tiver tempo foque no ranking dos melhores vendedores;

//    @GetMapping("/ranking")
//    public ResponseEntity<?> getRanking(@RequestParam LocalDate fromDate,
//                                        @RequestParam LocalDate toDate) {
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
