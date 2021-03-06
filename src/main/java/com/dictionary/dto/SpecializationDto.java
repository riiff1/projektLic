package com.dictionary.dto;

import java.io.Serializable;

public class SpecializationDto implements Serializable {
    private static final long serialVersionUID = 8287534153350652290L;
    private long specializationId;
    private String name;
    private int prize;

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
