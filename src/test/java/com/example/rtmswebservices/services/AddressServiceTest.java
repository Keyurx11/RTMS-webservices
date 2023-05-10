package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.AddressDTO;
import com.example.rtmswebservices.entities.Address;
import com.example.rtmswebservices.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private AddressDTO addressDTO;
    private Address address;

    @BeforeEach
    public void setUp() {
        addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setStreet("123 Main St");
        addressDTO.setCity("Anytown");
        addressDTO.setState("CA");
        addressDTO.setPostalCode("12345");
        addressDTO.setCountry("USA");

        address = new Address();
        address.setId(1L);
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setState("CA");
        address.setPostalCode("12345");
        address.setCountry("USA");
    }

    @Test
    public void testCreateAddress() {
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressDTO result = addressService.createAddress(addressDTO);

        assertEquals(addressDTO.getId(), result.getId());
        assertEquals(addressDTO.getStreet(), result.getStreet());
        assertEquals(addressDTO.getCity(), result.getCity());
        assertEquals(addressDTO.getState(), result.getState());
        assertEquals(addressDTO.getPostalCode(), result.getPostalCode());
        assertEquals(addressDTO.getCountry(), result.getCountry());

        verify(addressRepository).save(any(Address.class));
    }

    @Test
    public void testUpdateAddress() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        AddressDTO updatedAddressDTO = new AddressDTO();
        updatedAddressDTO.setStreet("456 Elm St");
        updatedAddressDTO.setCity("Othertown");
        updatedAddressDTO.setState("NY");
        updatedAddressDTO.setPostalCode("67890");
        updatedAddressDTO.setCountry("USA");

        AddressDTO result = addressService.updateAddress(1L, updatedAddressDTO);

        assertEquals(addressDTO.getId(), result.getId());
        assertEquals(updatedAddressDTO.getStreet(), result.getStreet());
        assertEquals(updatedAddressDTO.getCity(), result.getCity());
        assertEquals(updatedAddressDTO.getState(), result.getState());
        assertEquals(updatedAddressDTO.getPostalCode(), result.getPostalCode());
        assertEquals(updatedAddressDTO.getCountry(), result.getCountry());

        verify(addressRepository).findById(1L);
        verify(addressRepository).save(any(Address.class));
    }

    @Test
    public void testUpdateAddressWithNonExistingAddress() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> addressService.updateAddress(1L, addressDTO));

        verify(addressRepository).findById(1L);
        verify(addressRepository, never()).save(any(Address.class));
    }

    @Test
    public void testDeleteAddress() {
        doNothing().when(addressRepository).deleteById(1L);

        addressService.deleteAddress(1L);

        verify(addressRepository).deleteById(1L);
    }

    @Test
    public void testGetAddressByIdWithExistingAddress() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        AddressDTO result = addressService.getAddressById(1L);

        assertEquals(addressDTO.getId(), result.getId());
        assertEquals(addressDTO.getStreet(), result.getStreet());
        assertEquals(addressDTO.getCity(), result.getCity());
        assertEquals(addressDTO.getState(), result.getState());
        assertEquals(addressDTO.getPostalCode(), result.getPostalCode());
        assertEquals(addressDTO.getCountry(), result.getCountry());

        verify(addressRepository).findById(1L);
    }
}