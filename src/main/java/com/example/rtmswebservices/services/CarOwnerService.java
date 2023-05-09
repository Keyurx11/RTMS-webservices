package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.CarOwnerDTO;
import com.example.rtmswebservices.entities.Address;
import com.example.rtmswebservices.entities.CarOwner;
import com.example.rtmswebservices.repositories.AddressRepository;
import com.example.rtmswebservices.repositories.CarOwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarOwnerService {

    private final CarOwnerRepository carOwnerRepository;
    private final AddressRepository addressRepository;


    public CarOwnerService(CarOwnerRepository carOwnerRepository, AddressRepository addressRepository) {
        this.carOwnerRepository = carOwnerRepository;
        this.addressRepository = addressRepository;
    }

    public CarOwnerDTO createCarOwner(CarOwnerDTO carOwnerDTO) {
        CarOwner carOwner = convertToEntity(carOwnerDTO);
        CarOwner savedCarOwner = carOwnerRepository.save(carOwner);
        return convertToDTO(savedCarOwner);
    }

    public CarOwnerDTO getCarOwnerById(Long id) {
        CarOwner carOwner = carOwnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarOwner not found"));
        return convertToDTO(carOwner);
    }

    public List<CarOwnerDTO> getAllCarOwners() {
        List<CarOwner> carOwners = (List<CarOwner>) carOwnerRepository.findAll();
        return carOwners.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CarOwnerDTO updateCarOwner(Long id, CarOwnerDTO carOwnerDTO) {
        CarOwner existingCarOwner = carOwnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarOwner not found"));

        existingCarOwner.setFirstName(carOwnerDTO.getFirstName());
        existingCarOwner.setLastName(carOwnerDTO.getLastName());
        existingCarOwner.setEmail(carOwnerDTO.getEmail());
        existingCarOwner.setPhoneNumber(carOwnerDTO.getPhoneNumber());
        CarOwner updatedCarOwner = carOwnerRepository.save(existingCarOwner);
        return convertToDTO(updatedCarOwner);
    }

    public void deleteCarOwner(Long id) {
        CarOwner carOwner = carOwnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarOwner not found"));
        carOwnerRepository.delete(carOwner);
    }

    private CarOwner convertToEntity(CarOwnerDTO carOwnerDTO) {
        CarOwner carOwner = new CarOwner();
        carOwner.setFirstName(carOwnerDTO.getFirstName());
        carOwner.setLastName(carOwnerDTO.getLastName());
        carOwner.setEmail(carOwnerDTO.getEmail());
        carOwner.setPhoneNumber(carOwnerDTO.getPhoneNumber());

        if (carOwnerDTO.getAddressId() != null) {
            Address address = addressRepository.findById(carOwnerDTO.getAddressId())
                    .orElseThrow(() -> new EntityNotFoundException("Address not found"));
            carOwner.setAddress(address);
        } else {
            carOwner.setAddress(null);
        }
        return carOwner;
    }

    private CarOwnerDTO convertToDTO(CarOwner carOwner) {
        CarOwnerDTO carOwnerDTO = new CarOwnerDTO();
        carOwnerDTO.setId(carOwner.getId());
        carOwnerDTO.setFirstName(carOwner.getFirstName());
        carOwnerDTO.setLastName(carOwner.getLastName());
        carOwnerDTO.setEmail(carOwner.getEmail());
        carOwnerDTO.setPhoneNumber(carOwner.getPhoneNumber());
        if (carOwner.getAddress() != null) {
            carOwnerDTO.setAddressId(carOwner.getAddress().getId());
        } else {
            carOwnerDTO.setAddressId(null);
        }
        return carOwnerDTO;
    }

}
