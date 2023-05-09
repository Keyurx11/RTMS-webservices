package com.example.rtmswebservices.repositories;

import com.example.rtmswebservices.entities.TaxRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRateRepository extends CrudRepository<TaxRate, Long> {
}
