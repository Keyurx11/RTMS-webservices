package com.example.rtmswebservices.repositories;

import com.example.rtmswebservices.entities.CarRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRegistrationRepository extends CrudRepository<CarRegistration, Long> {
}
