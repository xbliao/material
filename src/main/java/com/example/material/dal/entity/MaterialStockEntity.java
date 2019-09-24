package com.example.material.dal.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * MaterialStockEntity.java
 *
 * @author xbliao   2019/9/24
 */
@Entity
@Table(name = "material_stock", schema = "person_test")
public class MaterialStockEntity {

    private long id;
    private String companyName;
    private String materialCode;
    private String materialDesc;
    private int number;
    private BigDecimal amount;
    private BigDecimal differenceAmount;
    private BigDecimal fee;
    private BigDecimal manualAmount;
    private BigDecimal materialAmount;
    private String type;
    private String status;
    private String shortTime;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "material_code")
    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    @Basic
    @Column(name = "material_desc")
    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "difference_amount")
    public BigDecimal getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(BigDecimal differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    @Basic
    @Column(name = "fee")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "manual_amount")
    public BigDecimal getManualAmount() {
        return manualAmount;
    }

    public void setManualAmount(BigDecimal manualAmount) {
        this.manualAmount = manualAmount;
    }

    @Basic
    @Column(name = "material_amount")
    public BigDecimal getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(BigDecimal materialAmount) {
        this.materialAmount = materialAmount;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "short_time")
    public String getShortTime() {
        return shortTime;
    }

    public void setShortTime(String shortTime) {
        this.shortTime = shortTime;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaterialStockEntity that = (MaterialStockEntity) o;

        if (id != that.id) return false;
        if (number != that.number) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (materialCode != null ? !materialCode.equals(that.materialCode) : that.materialCode != null) return false;
        if (materialDesc != null ? !materialDesc.equals(that.materialDesc) : that.materialDesc != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (differenceAmount != null ? !differenceAmount.equals(that.differenceAmount) : that.differenceAmount != null)
            return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;
        if (manualAmount != null ? !manualAmount.equals(that.manualAmount) : that.manualAmount != null) return false;
        if (materialAmount != null ? !materialAmount.equals(that.materialAmount) : that.materialAmount != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (shortTime != null ? !shortTime.equals(that.shortTime) : that.shortTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (materialCode != null ? materialCode.hashCode() : 0);
        result = 31 * result + (materialDesc != null ? materialDesc.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (differenceAmount != null ? differenceAmount.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (manualAmount != null ? manualAmount.hashCode() : 0);
        result = 31 * result + (materialAmount != null ? materialAmount.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (shortTime != null ? shortTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}