package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.TaxCategoryDTO;
import com.example.rtmswebservices.entities.TaxCategory;
import com.example.rtmswebservices.repositories.TaxCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxCategoryService {

    @Autowired
    private TaxCategoryRepository taxCategoryRepository;

    public TaxCategoryDTO createTaxCategory(TaxCategoryDTO taxCategoryDTO) {
        TaxCategory taxCategory = convertToEntity(taxCategoryDTO);
        TaxCategory savedTaxCategory = taxCategoryRepository.save(taxCategory);
        return convertToDTO(savedTaxCategory);
    }

    public TaxCategoryDTO getTaxCategoryById(Long id) {
        TaxCategory taxCategory = taxCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaxCategory not found"));
        return convertToDTO(taxCategory);
    }

    public List<TaxCategoryDTO> getAllTaxCategories() {
        List<TaxCategory> taxCategories = (List<TaxCategory>) taxCategoryRepository.findAll();
        return taxCategories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public TaxCategoryDTO updateTaxCategory(Long id, TaxCategoryDTO taxCategoryDTO) {
        TaxCategory existingTaxCategory = taxCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaxCategory not found"));

        existingTaxCategory.setName(taxCategoryDTO.getName());
        existingTaxCategory.setDescription(taxCategoryDTO.getDescription());
        TaxCategory updatedTaxCategory = taxCategoryRepository.save(existingTaxCategory);
        return convertToDTO(updatedTaxCategory);
    }

    public void deleteTaxCategory(Long id) {
        TaxCategory taxCategory = taxCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaxCategory not found"));
        taxCategoryRepository.delete(taxCategory);
    }

    private TaxCategory convertToEntity(TaxCategoryDTO taxCategoryDTO) {
        TaxCategory taxCategory = new TaxCategory();
        taxCategory.setName(taxCategoryDTO.getName());
        taxCategory.setDescription(taxCategoryDTO.getDescription());
        taxCategory.setTaxRateId(taxCategoryDTO.getTaxRateId());
        return taxCategory;
    }

    private TaxCategoryDTO convertToDTO(TaxCategory taxCategory) {
        TaxCategoryDTO taxCategoryDTO = new TaxCategoryDTO();
        taxCategoryDTO.setId(taxCategory.getId());
        taxCategoryDTO.setName(taxCategory.getName());
        taxCategoryDTO.setDescription(taxCategory.getDescription());
        taxCategoryDTO.setTaxRateId(taxCategory.getTaxRateId());
        return taxCategoryDTO;
    }
}
