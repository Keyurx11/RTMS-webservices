package com.example.rtmswebservices.repositories;

import com.example.rtmswebservices.entities.CarOwner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerRepository extends CrudRepository<CarOwner, Long> {
}
