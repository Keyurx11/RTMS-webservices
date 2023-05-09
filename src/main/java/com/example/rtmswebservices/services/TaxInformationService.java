package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.TaxInformationDTO;
import com.example.rtmswebservices.entities.CarRegistration;
import com.example.rtmswebservices.entities.TaxInformation;
import com.example.rtmswebservices.repositories.CarRegistrationRepository;
import com.example.rtmswebservices.repositories.TaxInformationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxInformationService {

    @Autowired
    private TaxInformationRepository taxInformationRepository;

    @Autowired
    private CarRegistrationRepository carRegistrationRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TaxInformationDTO createTaxInformation(TaxInformationDTO taxInformationDTO) {
        TaxInformation taxInformation = convertToEntity(taxInformationDTO);
        TaxInformation savedTaxInformation = taxInformationRepository.save(taxInformation);
        return convertToDTO(savedTaxInformation);
    }

    public TaxInformationDTO updateTaxInformation(Long id, TaxInformationDTO taxInformationDTO) {
        TaxInformation existingTaxInformation = taxInformationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaxInformation not found"));
        existingTaxInformation.setStartDate(LocalDate.parse(taxInformationDTO.getStartDate(), formatter));
        existingTaxInformation.setEndDate(LocalDate.parse(taxInformationDTO.getEndDate(), formatter));
        existingTaxInformation.setAmount(taxInformationDTO.getAmount());

        CarRegistration carRegistration = carRegistrationRepository.findById(taxInformationDTO.getCarRegistrationId())
                .orElseThrow(() -> new EntityNotFoundException("CarRegistration not found"));
        existingTaxInformation.setCarRegistration(carRegistration);

        TaxInformation updatedTaxInformation = taxInformationRepository.save(existingTaxInformation);
        return convertToDTO(updatedTaxInformation);
    }


    public void deleteTaxInformation(Long id) {
        taxInformationRepository.deleteById(id);
    }

    public TaxInformationDTO getTaxInformationById(Long id) {
        TaxInformation taxInformation = taxInformationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaxInformation not found"));
        return convertToDTO(taxInformation);
    }
    public List<TaxInformationDTO> getAllTaxInformation() {
        List<TaxInformation> taxInformationList = (List<TaxInformation>) taxInformationRepository.findAll();
        return taxInformationList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private TaxInformation convertToEntity(TaxInformationDTO taxInformationDTO) {
        TaxInformation taxInformation = new TaxInformation();
        taxInformation.setId(taxInformationDTO.getId());
        taxInformation.setStartDate(LocalDate.parse(taxInformationDTO.getStartDate(), formatter));
        taxInformation.setEndDate(LocalDate.parse(taxInformationDTO.getEndDate(), formatter));
        taxInformation.setAmount(taxInformationDTO.getAmount());

        CarRegistration carRegistration = carRegistrationRepository.findById(taxInformationDTO.getCarRegistrationId())
                .orElseThrow(() -> new EntityNotFoundException("CarRegistration not found"));
        taxInformation.setCarRegistration(carRegistration);

        return taxInformation;
    }

    private TaxInformationDTO convertToDTO(TaxInformation taxInformation) {
        TaxInformationDTO taxInformationDTO = new TaxInformationDTO();
        taxInformationDTO.setId(taxInformation.getId());
        taxInformationDTO.setStartDate(taxInformation.getStartDate().format(formatter));
        taxInformationDTO.setEndDate(taxInformation.getEndDate().format(formatter));
        taxInformationDTO.setAmount(taxInformation.getAmount());
        taxInformationDTO.setCarRegistrationId(taxInformation.getCarRegistration().getId());
        return taxInformationDTO;
    }

}
