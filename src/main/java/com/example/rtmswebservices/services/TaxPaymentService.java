package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.TaxPaymentDTO;
import com.example.rtmswebservices.entities.TaxPayment;
import com.example.rtmswebservices.repositories.CarRegistrationRepository;
import com.example.rtmswebservices.repositories.TaxInformationRepository;
import com.example.rtmswebservices.repositories.TaxPaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxPaymentService {

    private final TaxPaymentRepository taxPaymentRepository;
    private final TaxInformationRepository taxInformationRepository;
    private final CarRegistrationRepository carRegistrationRepository;

    public TaxPaymentService(TaxPaymentRepository taxPaymentRepository, TaxInformationRepository taxInformationRepository, CarRegistrationRepository carRegistrationRepository) {
        this.taxPaymentRepository = taxPaymentRepository;
        this.taxInformationRepository = taxInformationRepository;
        this.carRegistrationRepository = carRegistrationRepository;
    }

    public TaxPaymentDTO createTaxPayment(TaxPaymentDTO taxPaymentDTO) {
        TaxPayment taxPayment = convertToEntity(taxPaymentDTO);
        TaxPayment savedTaxPayment = taxPaymentRepository.save(taxPayment);
        return convertToDTO(savedTaxPayment);
    }


    public TaxPaymentDTO getTaxPaymentById(Long id) {
        TaxPayment taxPayment = taxPaymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TaxPayment not found"));
        return convertToDTO(taxPayment);
    }

    public List<TaxPaymentDTO> getAllTaxPayments() {
        List<TaxPayment> taxPayments = (List<TaxPayment>) taxPaymentRepository.findAll();
        return taxPayments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteTaxPayment(Long id) {
        TaxPayment taxPayment = taxPaymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaxPayment not found"));
        taxPaymentRepository.delete(taxPayment);
    }



    public List<TaxPaymentDTO> getTaxPaymentsByCarRegistrationId(Long carRegistrationId) {
        List<TaxPayment> taxPayments = taxPaymentRepository.findByCarRegistrationId(carRegistrationId);
        return taxPayments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    private TaxPayment convertToEntity(TaxPaymentDTO taxPaymentDTO) {
        TaxPayment taxPayment = new TaxPayment();
        taxPayment.setPaymentDate(taxPaymentDTO.getPaymentDate());
        taxPayment.setAmount(taxPaymentDTO.getAmount());
        taxPayment.setCarRegistration(carRegistrationRepository.findById(taxPaymentDTO.getCarRegistrationId()).get());
        return taxPayment;
    }

    private TaxPaymentDTO convertToDTO(TaxPayment taxPayment) {
        TaxPaymentDTO taxPaymentDTO = new TaxPaymentDTO();
        taxPaymentDTO.setId(taxPayment.getId());
        taxPaymentDTO.setPaymentDate(taxPayment.getPaymentDate());
        taxPaymentDTO.setAmount(taxPayment.getAmount());
        taxPaymentDTO.setCarRegistrationId(taxPayment.getCarRegistration().getId());
        return taxPaymentDTO;
    }
}