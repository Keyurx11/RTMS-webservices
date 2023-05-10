package com.example.rtmswebservices.services;

import com.example.rtmswebservices.dtos.CarOwnerDTO;
import com.example.rtmswebservices.entities.Address;
import com.example.rtmswebservices.entities.CarOwner;
import com.example.rtmswebservices.repositories.AddressRepository;
import com.example.rtmswebservices.repositories.CarOwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarOwnerServiceTest {

    @Mock
    private CarOwnerRepository carOwnerRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private CarOwnerService carOwnerService;

    private CarOwnerDTO carOwnerDTO;
    private CarOwner carOwner;
    private Address address;

    @BeforeEach
    public void setUp() {
        address = new Address();
        address.setId(1L);

        carOwnerDTO = new CarOwnerDTO();
        carOwnerDTO.setFirstName("John");
        carOwnerDTO.setLastName("Doe");
        carOwnerDTO.setEmail("john.doe@example.com");
        carOwnerDTO.setPhoneNumber("1234567890");
        carOwnerDTO.setAddressId(1L);

        carOwner = new CarOwner();
        carOwner.setId(1L);
        carOwner.setFirstName("John");
        carOwner.setLastName("Doe");
        carOwner.setEmail("john.doe@example.com");
        carOwner.setPhoneNumber("1234567890");
        carOwner.setAddress(address);
    }

    @Test
    public void testCreateCarOwnerWithNonExistingAddress() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> carOwnerService.createCarOwner(carOwnerDTO));

        verify(addressRepository).findById(1L);
        verify(carOwnerRepository, never()).save(any(CarOwner.class));
    }

    @Test
    public void testGetCarOwnerByIdWithExistingCarOwner() {
        when(carOwnerRepository.findById(1L)).thenReturn(Optional.of(carOwner));

        CarOwnerDTO result = carOwnerService.getCarOwnerById(1L);

        assertEquals(carOwnerDTO.getFirstName(), result.getFirstName());
        assertEquals(carOwnerDTO.getLastName(), result.getLastName());
        assertEquals(carOwnerDTO.getEmail(), result.getEmail());
        assertEquals(carOwnerDTO.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(carOwnerDTO.getAddressId(), result.getAddressId());

        verify(carOwnerRepository).findById(1L);
    }

    @Test
    public void testGetCarOwnerByIdWithNonExistingCarOwner() {
        when(carOwnerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> carOwnerService.getCarOwnerById(1L));

        verify(carOwnerRepository).findById(1L);
    }

    @Test
    public void testGetAllCarOwners() {
        List<CarOwner> carOwners = Arrays.asList(carOwner);
        when(carOwnerRepository.findAll()).thenReturn(carOwners);

        List<CarOwnerDTO> result = carOwnerService.getAllCarOwners();

        assertEquals(1, result.size());
        assertEquals(carOwnerDTO.getFirstName(), result.get(0).getFirstName());
        assertEquals(carOwnerDTO.getLastName(), result.get(0).getLastName());
        assertEquals(carOwnerDTO.getEmail(), result.get(0).getEmail());
        assertEquals(carOwnerDTO.getPhoneNumber(), result.get(0).getPhoneNumber());
        assertEquals(carOwnerDTO.getAddressId(), result.get(0).getAddressId());

        verify(carOwnerRepository).findAll();
    }

    @Test
    public void testUpdateCarOwnerWithNonExistingCarOwner() {
        when(carOwnerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> carOwnerService.updateCarOwner(1L, carOwnerDTO));

        verify(carOwnerRepository).findById(1L);
        verify(carOwnerRepository, never()).save(any(CarOwner.class));
    }

    @Test
    public void testDeleteCarOwnerWithExistingCarOwner() {
        when(carOwnerRepository.findById(1L)).thenReturn(Optional.of(carOwner));

        carOwnerService.deleteCarOwner(1L);

        verify(carOwnerRepository).findById(1L);
        verify(carOwnerRepository).delete(carOwner);
    }

    @Test
    public void testDeleteCarOwnerWithNonExistingCarOwner() {
        when(carOwnerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> carOwnerService.deleteCarOwner(1L));

        verify(carOwnerRepository).findById(1L);
        verify(carOwnerRepository, never()).delete(any(CarOwner.class));
    }
}



