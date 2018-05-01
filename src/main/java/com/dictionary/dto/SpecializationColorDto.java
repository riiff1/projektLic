package com.dictionary.dto;

import java.io.Serializable;

public class SpecializationColorDto implements Serializable {

    private static final long serialVersionUID = 2042883601086990073L;
    private long specializationColorId;
    private long userId;
    private long specializationId;
    private String color;
    private String specName;

    public long getSpecializationColorId() {
        return specializationColorId;
    }

    public void setSpecializationColorId(long specializationColorId) {
        this.specializationColorId = specializationColorId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
