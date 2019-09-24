package com.example.material.service;

import com.example.material.service.dto.UnitMaterialStore;

import java.math.BigDecimal;

/**
 * StoreCalculateBean.java
 *
 * @author xbliao   2019/9/24
 */
public class StoreCalculateBean {


    private static BigDecimal coefficient;

    public StoreCalculateBean(int outboundNumber, int initialNumber, int inboundNumber) {
        coefficient = calculateCoefficient(outboundNumber, initialNumber, inboundNumber);
    }


    //出库计算
    public UnitMaterialStore calculateOutbound(UnitMaterialStore initialStore, UnitMaterialStore inboundStore
            , int outboundNumber, BigDecimal outboundAmount) {

        UnitMaterialStore outboundStore = new UnitMaterialStore();
        outboundStore.setNumber(outboundNumber);
        outboundStore.setAmount(outboundAmount);
        outboundStore.setDifferenceAmount(calculateOutbound(initialStore.getDifferenceAmount(), inboundStore.getDifferenceAmount()));
        outboundStore.setFee(calculateOutbound(initialStore.getDifferenceAmount(), inboundStore.getDifferenceAmount()));
        outboundStore.setManualAmount(calculateOutbound(initialStore.getManualAmount(), inboundStore.getManualAmount()));
        outboundStore.setMaterialAmount(calculateOutbound(initialStore.getMaterialAmount(), inboundStore.getMaterialAmount()));
        return outboundStore;
    }

    //结存计算
    public UnitMaterialStore calculateSurplusBound(UnitMaterialStore initialStore, UnitMaterialStore inboundStore
            , UnitMaterialStore outboundStore) {

        UnitMaterialStore surplusBoundStore = new UnitMaterialStore();

        surplusBoundStore.setNumber(initialStore.getNumber() + inboundStore.getNumber() + outboundStore.getNumber());
        surplusBoundStore.setAmount(initialStore.getAmount().add(inboundStore.getAmount()).add(inboundStore.getAmount()));
        surplusBoundStore.setDifferenceAmount(calculateSurplus(initialStore.getDifferenceAmount(), inboundStore.getDifferenceAmount()));
        surplusBoundStore.setFee(calculateSurplus(initialStore.getDifferenceAmount(), inboundStore.getDifferenceAmount()));
        surplusBoundStore.setManualAmount(calculateSurplus(initialStore.getManualAmount(), inboundStore.getManualAmount()));
        surplusBoundStore.setMaterialAmount(calculateSurplus(initialStore.getMaterialAmount(), inboundStore.getMaterialAmount()));
        return surplusBoundStore;
    }


    //计算出库
    private BigDecimal calculateOutbound(BigDecimal initialVal, BigDecimal inboundVal) {
        return initialVal.add(inboundVal).multiply(coefficient);
    }

    //计算结存
    private BigDecimal calculateSurplus(BigDecimal val1, BigDecimal val2) {
        return BigDecimal.ONE.subtract(calculateOutbound(val1, val2));
    }

    //关系数
    private static BigDecimal calculateCoefficient(int outboundNumber, int initialNumber, int inboundNumber) {
        BigDecimal denominator = BigDecimal.valueOf(initialNumber).add(BigDecimal.valueOf(inboundNumber));
        if (BigDecimal.ZERO.equals(denominator)) {
            return BigDecimal.ZERO;
        } else {
            return BigDecimal.valueOf(outboundNumber).divide(denominator, 4, BigDecimal.ROUND_HALF_UP);
        }
    }


}