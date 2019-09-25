package com.example.material.service.dto;


import com.example.material.dal.constant.StoreOperateType;
import com.example.material.service.MaterialStoreHandler;

import java.math.BigDecimal;

/**
 * InsertMaterialStoreParam.java
 * 入库信息
 *
 * @author xbliao   2019/9/24
 */
public class InsertMaterialStoreParam {

    private String type;
    private String companyName;
    private String materialCode;
    private String materialDesc;
    private int number;
    private BigDecimal amount;
    private BigDecimal differenceAmount;
    private BigDecimal fee;
    private BigDecimal manualAmount;
    private BigDecimal materialAmount;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

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

    public InsertMaterialStoreParam() {
    }


    public InsertMaterialStoreParam(StoreOperateType operateType, MaterialStoreHandler.MaterialInfo materialInfo, UnitMaterialStore unitMaterialStore) {
        this.type = operateType.getType();
        this.companyName = materialInfo.getCompanyName();
        this.materialCode = materialInfo.getMaterialCode();
        this.materialDesc = materialInfo.getMaterialDesc();
        this.number = unitMaterialStore.getNumber();
        this.amount = unitMaterialStore.getAmount();
        this.differenceAmount = unitMaterialStore.getDifferenceAmount();
        this.fee = unitMaterialStore.getFee();
        this.manualAmount = unitMaterialStore.getManualAmount();
        this.materialAmount = unitMaterialStore.getMaterialAmount();

    }


}