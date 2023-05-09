package com.example.rtmswebservices.controllers;

import com.example.rtmswebservices.dtos.TaxInformationDTO;
import com.example.rtmswebservices.services.TaxInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxinformation")
public class TaxInformationController {

    private final TaxInformationService taxInformationService;

    public TaxInformationController(TaxInformationService taxInformationService) {
        this.taxInformationService = taxInformationService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaxInformationDTO> createTaxInformation(@RequestBody TaxInformationDTO taxInformationDTO) {
        TaxInformationDTO createdTaxInformation = taxInformationService.createTaxInformation(taxInformationDTO);
        return new ResponseEntity<>(createdTaxInformation, HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<TaxInformationDTO> getTaxInformationById(@PathVariable Long id) {
        TaxInformationDTO taxInformation = taxInformationService.getTaxInformationById(id);
        return new ResponseEntity<>(taxInformation, HttpStatus.OK);
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<TaxInformationDTO>> getAllTaxInformation() {
        List<TaxInformationDTO> taxInformationList = taxInformationService.getAllTaxInformation();
        return new ResponseEntity<>(taxInformationList, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TaxInformationDTO> updateTaxInformation(@PathVariable Long id, @RequestBody TaxInformationDTO taxInformationDTO) {
        TaxInformationDTO updatedTaxInformation = taxInformationService.updateTaxInformation(id, taxInformationDTO);
        return new ResponseEntity<>(updatedTaxInformation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaxInformation(@PathVariable Long id) {
        taxInformationService.deleteTaxInformation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
