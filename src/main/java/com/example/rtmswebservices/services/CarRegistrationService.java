package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.CarRegistrationDTO;
import com.example.rtmswebservices.entities.Address;
import com.example.rtmswebservices.entities.CarOwner;
import com.example.rtmswebservices.entities.CarRegistration;
import com.example.rtmswebservices.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarRegistrationService {

    @Autowired
    private CarRegistrationRepository carRegistrationRepository;

    @Autowired
    private CarOwnerRepository carOwnerRepository;

    @Autowired
    private TaxCategoryRepository taxCategoryRepository;

    @Autowired
    private AddressRepository addressRepository;

    public CarRegistrationDTO createCarRegistration(CarRegistrationDTO carRegistrationDTO) {
        CarRegistration carRegistration = convertToEntity(carRegistrationDTO);
        CarRegistration savedCarRegistration = carRegistrationRepository.save(carRegistration);
        return convertToDTO(savedCarRegistration);
    }


    public CarRegistrationDTO updateCarRegistration(Long id, CarRegistrationDTO carRegistrationDTO) {
        CarRegistration existingCarRegistration = carRegistrationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CarRegistration not found"));
        existingCarRegistration.setRegistrationNumber(carRegistrationDTO.getRegistrationNumber());
        existingCarRegistration.setMake(carRegistrationDTO.getMake());
        existingCarRegistration.setModel(carRegistrationDTO.getModel());
        existingCarRegistration.setYear(carRegistrationDTO.getYear());
        existingCarRegistration.setTaxCategory(taxCategoryRepository.findById(carRegistrationDTO.getTaxCategoryId()).orElseThrow(() -> new EntityNotFoundException("TaxCategory not found")));
        existingCarRegistration.setCarOwner(carOwnerRepository.findById(carRegistrationDTO.getCarOwnerId()).orElseThrow(() -> new EntityNotFoundException("CarOwner not found")));
        CarRegistration updatedCarRegistration = carRegistrationRepository.save(existingCarRegistration);
        return convertToDTO(updatedCarRegistration);
    }


    public void deleteCarRegistration(Long id) {
        carRegistrationRepository.deleteById(id);
    }

    public CarRegistrationDTO getCarRegistrationById(Long id) {
        CarRegistration carRegistration = carRegistrationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CarRegistration not found"));
        return convertToDTO(carRegistration);
    }


    public List<CarRegistrationDTO> getAllCarRegistrations() {
        List<CarRegistration> carRegistrations = (List<CarRegistration>) carRegistrationRepository.findAll();
        return carRegistrations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CarRegistration convertToEntity(CarRegistrationDTO carRegistrationDTO) {
        CarRegistration carRegistration = new CarRegistration();
        carRegistration.setRegistrationNumber(carRegistrationDTO.getRegistrationNumber());
        Optional<CarOwner> carOwner = carOwnerRepository.findById(carRegistrationDTO.getCarOwnerId());
        Optional<Address> address = addressRepository.findById(carOwner.get().getAddress().getId());
        System.out.println("Find MEEEEEEE");
        System.out.println(address.get().getCity());
        carRegistration.setCarOwner(carOwner.get());
        carRegistration.setMake(carRegistrationDTO.getMake());
        carRegistration.setModel(carRegistrationDTO.getModel());
        carRegistration.setYear(carRegistrationDTO.getYear());
        carRegistration.setTaxCategory(taxCategoryRepository.findById(carRegistrationDTO.getTaxCategoryId()).get());
        return carRegistration;
    }

    private CarRegistrationDTO convertToDTO(CarRegistration carRegistration) {
        CarRegistrationDTO carRegistrationDTO = new CarRegistrationDTO();
        carRegistrationDTO.setId(carRegistration.getId());
        carRegistrationDTO.setMake(carRegistration.getMake());
        carRegistrationDTO.setModel(carRegistration.getModel());
        carRegistrationDTO.setYear(carRegistration.getYear());
        carRegistrationDTO.setRegistrationNumber(carRegistration.getRegistrationNumber());
        carRegistrationDTO.setCarOwnerId(carRegistration.getCarOwner().getId());
        return carRegistrationDTO;
    }


}
