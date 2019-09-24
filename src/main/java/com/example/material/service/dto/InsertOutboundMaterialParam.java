package com.example.material.service.dto;

import java.math.BigDecimal;

/**
 * InsertOutboundMaterialParam.java
 *
 * @author xbliao   2019/9/24
 */
public class InsertOutboundMaterialParam {

    private int number;

    private BigDecimal amount;


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
}