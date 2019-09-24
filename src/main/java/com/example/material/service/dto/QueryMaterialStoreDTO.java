package com.example.material.service.dto;

/**
 * QueryMaterialStoreDTO.java
 * 库存结果集
 * @author xbliao   2019/9/24
 */
public class QueryMaterialStoreDTO {

    //公司
    private String companyName;
    //物料号
    private String materialCode;
    //描述
    private String materialDesc;

    //期初
    private UnitMaterialStore initialStore;
    //入库
    private UnitMaterialStore inboundStore;
    //出库
    private UnitMaterialStore outboundStore;
    //结存
    private UnitMaterialStore surplusStore;


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

    public UnitMaterialStore getInitialStore() {
        return initialStore;
    }

    public void setInitialStore(UnitMaterialStore initialStore) {
        this.initialStore = initialStore;
    }

    public UnitMaterialStore getInboundStore() {
        return inboundStore;
    }

    public void setInboundStore(UnitMaterialStore inboundStore) {
        this.inboundStore = inboundStore;
    }

    public UnitMaterialStore getOutboundStore() {
        return outboundStore;
    }

    public void setOutboundStore(UnitMaterialStore outboundStore) {
        this.outboundStore = outboundStore;
    }

    public UnitMaterialStore getSurplusStore() {
        return surplusStore;
    }

    public void setSurplusStore(UnitMaterialStore surplusStore) {
        this.surplusStore = surplusStore;
    }
}