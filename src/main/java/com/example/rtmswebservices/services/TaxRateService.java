package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.TaxRateDTO;
import com.example.rtmswebservices.entities.TaxRate;
import com.example.rtmswebservices.repositories.TaxRateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxRateService {

    @Autowired
    private TaxRateRepository taxRateRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TaxRateDTO createTaxRate(TaxRateDTO taxRateDTO) {
        TaxRate taxRate = convertToEntity(taxRateDTO);
        TaxRate savedTaxRate = taxRateRepository.save(taxRate);
        return convertToDTO(savedTaxRate);
    }

    public TaxRateDTO updateTaxRate(Long id, TaxRateDTO taxRateDTO) {
        TaxRate existingTaxRate = taxRateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaxRate not found"));
        existingTaxRate.setTaxCategoryId(taxRateDTO.getTaxCategoryId());
        existingTaxRate.setRate(taxRateDTO.getRate());
        existingTaxRate.setEffectiveDate(LocalDate.parse(taxRateDTO.getEffectiveDate(), formatter));
        existingTaxRate.setExpirationDate(LocalDate.parse(taxRateDTO.getExpirationDate(), formatter));
        TaxRate updatedTaxRate = taxRateRepository.save(existingTaxRate);
        return convertToDTO(updatedTaxRate);
    }

    public void deleteTaxRate(Long id) {
        taxRateRepository.deleteById(id);
    }

    public TaxRateDTO getTaxRateById(Long id) {
        TaxRate taxRate = taxRateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TaxRate not found"));
        return convertToDTO(taxRate);
    }

    public List<TaxRateDTO> getAllTaxRates() {
        List<TaxRate> taxRateList = (List<TaxRate>) taxRateRepository.findAll();
        return taxRateList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TaxRate convertToEntity(TaxRateDTO taxRateDTO) {
        TaxRate taxRate = new TaxRate();
        taxRate.setId(taxRateDTO.getId());
        taxRate.setTaxCategoryId(taxRateDTO.getTaxCategoryId());
        taxRate.setRate(taxRateDTO.getRate());
        taxRate.setEffectiveDate(LocalDate.parse(taxRateDTO.getEffectiveDate(), formatter));
        taxRate.setExpirationDate(LocalDate.parse(taxRateDTO.getExpirationDate(), formatter));
        return taxRate;
    }

    private TaxRateDTO convertToDTO(TaxRate taxRate) {
        TaxRateDTO taxRateDTO = new TaxRateDTO();
        taxRateDTO.setId(taxRate.getId());
        taxRateDTO.setTaxCategoryId(taxRate.getTaxCategoryId());
        taxRateDTO.setRate(taxRate.getRate());
        taxRateDTO.setEffectiveDate(taxRate.getEffectiveDate().format(formatter));
        taxRateDTO.setExpirationDate(taxRate.getExpirationDate().format(formatter));
        return taxRateDTO;
    }
}
