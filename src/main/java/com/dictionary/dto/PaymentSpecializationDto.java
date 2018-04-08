package com.dictionary.dto;

import java.io.Serializable;

public class PaymentSpecializationDto implements Serializable {
    private static final long serialVersionUID = 7057171105094218736L;
    private long paymentSpecializationId;
    private long paymentId;
    private long specializationId;

    public long getPaymentSpecializationId() {
        return paymentSpecializationId;
    }

    public void setPaymentSpecializationId(long paymentSpecializationId) {
        this.paymentSpecializationId = paymentSpecializationId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }
}
