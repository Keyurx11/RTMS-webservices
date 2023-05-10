package com.example.rtmswebservices.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class TaxInformationDTO implements Serializable {

    private Long id;
    private String startDate;
    private String endDate;
    private BigDecimal amount;
    private Long carRegistrationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
