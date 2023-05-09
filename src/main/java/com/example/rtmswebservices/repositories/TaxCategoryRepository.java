package com.example.rtmswebservices.repositories;

import com.example.rtmswebservices.entities.TaxCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxCategoryRepository extends CrudRepository<TaxCategory, Long> {
}
