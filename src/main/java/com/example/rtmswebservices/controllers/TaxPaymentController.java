package com.example.rtmswebservices.controllers;

import com.example.rtmswebservices.dtos.TaxPaymentDTO;
import com.example.rtmswebservices.services.TaxPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxpayment")
public class TaxPaymentController {

    private final TaxPaymentService taxPaymentService;

    public TaxPaymentController(TaxPaymentService taxPaymentService) {
        this.taxPaymentService = taxPaymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaxPaymentDTO> createTaxPayment(@RequestBody TaxPaymentDTO taxPaymentDTO) {
        TaxPaymentDTO createdTaxPayment = taxPaymentService.createTaxPayment(taxPaymentDTO);
        return new ResponseEntity<>(createdTaxPayment, HttpStatus.CREATED);
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<TaxPaymentDTO>> getAllTaxPayments() {
        List<TaxPaymentDTO> taxPayments = taxPaymentService.getAllTaxPayments();
        return new ResponseEntity<>(taxPayments, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<TaxPaymentDTO> getTaxPaymentById(@PathVariable Long id) {
        TaxPaymentDTO taxPayment = taxPaymentService.getTaxPaymentById(id);
        return new ResponseEntity<>(taxPayment, HttpStatus.OK);
    }


    @GetMapping("/search/taxpayment-by-carregistration/{carRegistrationId}")
    public ResponseEntity<List<TaxPaymentDTO>> getTaxPaymentsByCarRegistrationId(@PathVariable Long carRegistrationId) {
        List<TaxPaymentDTO> taxPayments = taxPaymentService.getTaxPaymentsByCarRegistrationId(carRegistrationId);
        return new ResponseEntity<>(taxPayments, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaxPayment(@PathVariable Long id) {
        taxPaymentService.deleteTaxPayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
