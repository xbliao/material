package com.example.material.service.dto;

import java.math.BigDecimal;

/**
 * UnitMaterialStore.java
 *
 * @author xbliao   2019/9/24
 */
public class UnitMaterialStore {

    private int number;
    private BigDecimal amount;
    private BigDecimal differenceAmount;
    private BigDecimal fee;
    private BigDecimal manualAmount;
    private BigDecimal materialAmount;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(BigDecimal differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getManualAmount() {
        return manualAmount;
    }

    public void setManualAmount(BigDecimal manualAmount) {
        this.manualAmount = manualAmount;
    }

    public BigDecimal getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(BigDecimal materialAmount) {
        this.materialAmount = materialAmount;
    }
}
