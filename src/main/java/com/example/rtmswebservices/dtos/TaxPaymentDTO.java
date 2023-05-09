package com.example.rtmswebservices.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TaxPaymentDTO implements Serializable {

    private Long id;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private Long carRegistrationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCarRegistrationId() {
        return carRegistrationId;
    }

    public void setCarRegistrationId(Long carRegistrationId) {
        this.carRegistrationId = carRegistrationId;
    }
}
