package com.example.rtmswebservices.repositories;

import com.example.rtmswebservices.entities.TaxPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxPaymentRepository extends CrudRepository<TaxPayment, Long> {
    List<TaxPayment> findByCarRegistrationId(Long carRegistrationId);

    Optional<TaxPayment> findByIdAndCarRegistrationId(Long id, Long carRegistrationId);
}
