package com.example.rtmswebservices.controllers;

import com.example.rtmswebservices.dtos.TaxRateDTO;
import com.example.rtmswebservices.services.TaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax-rates")
public class TaxRateController {

    @Autowired
    private TaxRateService taxRateService;

    @PostMapping("/create")
    public ResponseEntity<TaxRateDTO> createTaxRate(@RequestBody TaxRateDTO taxRateDTO) {
        TaxRateDTO createdTaxRate = taxRateService.createTaxRate(taxRateDTO);
        return new ResponseEntity<>(createdTaxRate, HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<TaxRateDTO> getTaxRateById(@PathVariable Long id) {
        TaxRateDTO taxRateDTO = taxRateService.getTaxRateById(id);
        return new ResponseEntity<>(taxRateDTO, HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<TaxRateDTO>> getAllTaxRates() {
        List<TaxRateDTO> taxRates = taxRateService.getAllTaxRates();
        return new ResponseEntity<>(taxRates, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TaxRateDTO> updateTaxRate(@PathVariable Long id, @RequestBody TaxRateDTO taxRateDTO) {
        TaxRateDTO updatedTaxRate = taxRateService.updateTaxRate(id, taxRateDTO);
        return new ResponseEntity<>(updatedTaxRate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaxRate(@PathVariable Long id) {
        taxRateService.deleteTaxRate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
