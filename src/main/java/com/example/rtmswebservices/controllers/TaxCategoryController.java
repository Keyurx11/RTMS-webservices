package com.example.rtmswebservices.controllers;

import com.example.rtmswebservices.dtos.TaxCategoryDTO;
import com.example.rtmswebservices.services.TaxCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tax-categories")
public class TaxCategoryController {

    @Autowired
    private TaxCategoryService taxCategoryService;

    @PostMapping("/create")
    public ResponseEntity<TaxCategoryDTO> createTaxCategory(@RequestBody TaxCategoryDTO taxCategoryDTO) {
        TaxCategoryDTO createdTaxCategory = taxCategoryService.createTaxCategory(taxCategoryDTO);
        return new ResponseEntity<>(createdTaxCategory, HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<TaxCategoryDTO> getTaxCategoryById(@PathVariable Long id) {
        TaxCategoryDTO taxCategoryDTO = taxCategoryService.getTaxCategoryById(id);
        return new ResponseEntity<>(taxCategoryDTO, HttpStatus.OK);
    }

    @GetMapping("search/all")
    public ResponseEntity<List<TaxCategoryDTO>> getAllTaxCategories() {
        List<TaxCategoryDTO> taxCategories = taxCategoryService.getAllTaxCategories();
        return new ResponseEntity<>(taxCategories, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TaxCategoryDTO> updateTaxCategory(@PathVariable Long id, @RequestBody TaxCategoryDTO taxCategoryDTO) {
        TaxCategoryDTO updatedTaxCategory = taxCategoryService.updateTaxCategory(id, taxCategoryDTO);
        return new ResponseEntity<>(updatedTaxCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaxCategory(@PathVariable Long id) {
        taxCategoryService.deleteTaxCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
