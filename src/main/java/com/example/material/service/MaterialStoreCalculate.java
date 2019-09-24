package com.example.material.service;

import com.example.material.dal.constant.StoreOperateType;
import com.example.material.dal.entity.MaterialStockEntity;
import com.example.material.service.dto.InsertMaterialStoreParam;
import com.example.material.service.dto.UnitMaterialStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * MaterialStoreCalculate.java
 *
 * @author xbliao   2019/9/24
 */

@Component
public class MaterialStoreCalculate {

    @Resource
    private MaterialStoreService materialStoreService;


    private void calculate(List<MaterialStockEntity> outboundList
            , List<MaterialStockEntity> initialList, List<MaterialStockEntity> inboundList) {

        Map<String, MaterialInfo> materialInfoMap = new HashMap<>();

        Map<String, MaterialStockEntity> outboundMap = new HashMap<>(outboundList.size());
        outboundList.forEach(outboundEntity -> {
            materialInfoMap.put(outboundEntity.getMaterialCode(), new MaterialInfo(outboundEntity));
            outboundMap.put(outboundEntity.getMaterialCode(), outboundEntity);
        });

        Map<String, MaterialStockEntity> initialMap = new HashMap<>(initialList.size());
        outboundList.forEach(initialEntity -> {
            materialInfoMap.put(initialEntity.getMaterialCode(), new MaterialInfo(initialEntity));
            initialMap.put(initialEntity.getMaterialCode(), initialEntity);
        });


        Map<String, MaterialStockEntity> inboundMap = new HashMap<>(inboundList.size());
        outboundList.forEach(inboundEntity -> {
            materialInfoMap.put(inboundEntity.getMaterialCode(), new MaterialInfo(inboundEntity));
            inboundMap.put(inboundEntity.getMaterialCode(), inboundEntity);
        });


        List<InsertMaterialStoreParam> outboundMaterialInsertParamList = new ArrayList<>(materialInfoMap.size());
        List<InsertMaterialStoreParam> surplusMaterialInsertParamList = new ArrayList<>(inboundMap.size());


        for (Map.Entry<String, MaterialInfo> entry : materialInfoMap.entrySet()) {
            MaterialStockEntity outboundEntity = outboundMap.get(entry.getKey());
            MaterialStockEntity initialEntity = initialMap.get(entry.getKey());
            MaterialStockEntity inboundEntity = inboundMap.get(entry.getKey());

            StoreCalculateBean calculateBean = new StoreCalculateBean(outboundEntity.getNumber(),
                    initialEntity.getNumber(), inboundEntity.getNumber());

            UnitMaterialStore outboundUnit = convertUnitMaterialStore(outboundEntity);
            UnitMaterialStore initialUnit = convertUnitMaterialStore(initialEntity);
            UnitMaterialStore inboundUnit = convertUnitMaterialStore(inboundEntity);

            UnitMaterialStore outboundCalculateResult = calculateBean.calculateOutbound(
                    initialUnit, inboundUnit, outboundEntity.getNumber(), outboundEntity.getAmount());
            UnitMaterialStore surplusBoundCalculateResult = calculateBean.calculateSurplusBound(initialUnit, inboundUnit, outboundUnit);


            InsertMaterialStoreParam outboundStoreParam = new InsertMaterialStoreParam(entry.getValue(), outboundCalculateResult);
            InsertMaterialStoreParam surplusBoundStoreParam = new InsertMaterialStoreParam(entry.getValue(), surplusBoundCalculateResult);


            outboundMaterialInsertParamList.add(outboundStoreParam);
            surplusMaterialInsertParamList.add(surplusBoundStoreParam);
        }

        materialStoreService.saveMaterialStock(StoreOperateType.OUT, outboundMaterialInsertParamList);

        materialStoreService.saveMaterialStock(StoreOperateType.SURPLUS, surplusMaterialInsertParamList);

    }


    private static UnitMaterialStore convertUnitMaterialStore(MaterialStockEntity materialStockEntity) {
        UnitMaterialStore unitMaterialStore = new UnitMaterialStore();
        unitMaterialStore.setMaterialAmount(materialStockEntity.getMaterialAmount());
        unitMaterialStore.setManualAmount(materialStockEntity.getManualAmount());
        unitMaterialStore.setFee(materialStockEntity.getFee());
        unitMaterialStore.setDifferenceAmount(materialStockEntity.getDifferenceAmount());
        unitMaterialStore.setNumber(materialStockEntity.getNumber());
        unitMaterialStore.setAmount(materialStockEntity.getAmount());

        return unitMaterialStore;
    }


    public class MaterialInfo {

        private String companyName;
        private String materialCode;
        private String materialDesc;


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


        MaterialInfo(MaterialStockEntity entity) {
            companyName = entity.getCompanyName();
            materialCode = entity.getMaterialCode();
            materialDesc = entity.getMaterialDesc();
        }

    }


}