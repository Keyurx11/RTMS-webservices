package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.AddressDTO;
import com.example.rtmswebservices.entities.Address;
import com.example.rtmswebservices.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = convertToAddressEntity(addressDTO);
        Address savedAddress = addressRepository.save(address);
        return convertToAddressDTO(savedAddress);
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
        existingAddress.setStreet(addressDTO.getStreet());
        existingAddress.setCity(addressDTO.getCity());
        existingAddress.setState(addressDTO.getState());
        existingAddress.setPostalCode(addressDTO.getPostalCode());
        existingAddress.setCountry(addressDTO.getCountry());
        Address updatedAddress = addressRepository.save(existingAddress);
        return convertToAddressDTO(updatedAddress);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
        return convertToAddressDTO(address);
    }

    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = (List<Address>) addressRepository.findAll();
        return addresses.stream().map(this::convertToAddressDTO).collect(Collectors.toList());
    }

    private Address convertToAddressEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

    private AddressDTO convertToAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }
}
