package com.example.rtmswebservices.controllers;

import com.example.rtmswebservices.dtos.CarRegistrationDTO;
import com.example.rtmswebservices.services.CarRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carregistrations")
public class CarRegistrationController {

    private final CarRegistrationService carRegistrationService;

    public CarRegistrationController(CarRegistrationService carRegistrationService) {
        this.carRegistrationService = carRegistrationService;
    }

    @PostMapping("/create")
    public ResponseEntity<CarRegistrationDTO> createCarRegistration(@RequestBody CarRegistrationDTO carRegistrationDTO) {
        CarRegistrationDTO createdCarRegistration = carRegistrationService.createCarRegistration(carRegistrationDTO);
        return new ResponseEntity<>(createdCarRegistration, HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<CarRegistrationDTO> getCarRegistrationById(@PathVariable Long id) {
        CarRegistrationDTO carRegistrationDTO = carRegistrationService.getCarRegistrationById(id);
        return new ResponseEntity<>(carRegistrationDTO, HttpStatus.OK);
    }


    @GetMapping("/search/all")
    public ResponseEntity<List<CarRegistrationDTO>> getAllCarRegistrations() {
        List<CarRegistrationDTO> carRegistrations = carRegistrationService.getAllCarRegistrations();
        return new ResponseEntity<>(carRegistrations, HttpStatus.OK);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<CarRegistrationDTO> updateCarRegistration(@PathVariable Long id, @RequestBody CarRegistrationDTO carRegistrationDTO) {
        CarRegistrationDTO updatedCarRegistrationDTO = carRegistrationService.updateCarRegistration(id, carRegistrationDTO);
        return new ResponseEntity<>(updatedCarRegistrationDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCarRegistration(@PathVariable Long id) {
        carRegistrationService.deleteCarRegistration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
