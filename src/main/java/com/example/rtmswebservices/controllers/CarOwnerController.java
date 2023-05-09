package com.example.rtmswebservices.controllers;

import com.example.rtmswebservices.dtos.CarOwnerDTO;
import com.example.rtmswebservices.services.CarOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carowners")
public class CarOwnerController {

    private final CarOwnerService carOwnerService;

    public CarOwnerController(CarOwnerService carOwnerService) {
        this.carOwnerService = carOwnerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CarOwnerDTO> createCarOwner(@RequestBody CarOwnerDTO carOwnerDTO) {
        CarOwnerDTO createdCarOwner = carOwnerService.createCarOwner(carOwnerDTO);
        return new ResponseEntity<>(createdCarOwner, HttpStatus.CREATED);
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<CarOwnerDTO>> getAllCarOwners() {
        List<CarOwnerDTO> carOwners = carOwnerService.getAllCarOwners();
        return new ResponseEntity<>(carOwners, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<CarOwnerDTO> getCarOwnerById(@PathVariable Long id) {
        CarOwnerDTO carOwner = carOwnerService.getCarOwnerById(id);
        return new ResponseEntity<>(carOwner, HttpStatus.OK);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<CarOwnerDTO> updateCarOwner(@PathVariable Long id, @RequestBody CarOwnerDTO carOwnerDTO) {
        CarOwnerDTO updatedCarOwner = carOwnerService.updateCarOwner(id, carOwnerDTO);
        return new ResponseEntity<>(updatedCarOwner, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCarOwner(@PathVariable Long id) {
        carOwnerService.deleteCarOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
