package com.example.rtmswebservices.repositories;

import com.example.rtmswebservices.entities.TaxInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxInformationRepository extends CrudRepository<TaxInformation, Long> {
}
