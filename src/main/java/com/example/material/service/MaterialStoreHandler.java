package com.example.material.service;

import com.example.material.common.ConvertUtil;
import com.example.material.dal.constant.StoreOperateType;
import com.example.material.dal.entity.MaterialStockEntity;
import com.example.material.service.dto.InsertMaterialStoreParam;
import com.example.material.service.dto.UnitMaterialStore;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * MaterialStoreHandler.java
 *
 * @author xbliao   2019/9/24
 */

@Component
public class MaterialStoreHandler {

    @Resource
    private MaterialStoreService materialStoreService;


    /**
     * 从该日期循环计算到这个月
     *
     * @param reCalculateDate 开始时间
     * @return 是否成功
     */
    public boolean cyclicCalculateAndSave(Date reCalculateDate) {


        Date calculateStartMonth = ConvertUtil.getBeginTimeOfTheDay(reCalculateDate);
        Date nowTimeStartMonth = ConvertUtil.getBeginTimeOfTheMonth(new Date());

        while (nowTimeStartMonth.getTime() >= calculateStartMonth.getTime()) {

            boolean result = this.calculateAndSave(reCalculateDate);
            if (!result) {
                System.out.println("error!!!!!!!!!!!!!!!!!!!!");
                return false;
            }
            calculateStartMonth = ConvertUtil.plusMonth(calculateStartMonth, 1);
        }

        return true;
    }


    /**
     * 对该code从这个日期循环计算到这个月
     *
     * @param materialCode    计算的材料编号
     * @param reCalculateDate 开始时间
     * @return 是否成功
     */
    public boolean cyclicCalculateAndSave(String materialCode, Date reCalculateDate) {


        Date calculateStartMonth = ConvertUtil.getBeginTimeOfTheDay(reCalculateDate);
        Date nowTimeStartMonth = ConvertUtil.getBeginTimeOfTheMonth(new Date());

        while (nowTimeStartMonth.getTime() >= calculateStartMonth.getTime()) {

            boolean result = this.calculateAndSave(reCalculateDate, materialCode);
            if (!result) {
                System.out.println("error!!!!!!!!!!!!!!!!!!!!");
                return false;
            }
            calculateStartMonth = ConvertUtil.plusMonth(calculateStartMonth, 1);
        }

        return true;
    }


    /**
     * 对某月数据之后的所有数据进行重新计算
     *
     * @param calculateDate 取计算开始月份
     * @return 是否成功
     */
    private boolean calculateAndSave(Date calculateDate) {

        Date preMonteDate = DateUtils.addMonths(calculateDate, -1);

        //查询参数
        List<MaterialStockEntity> inboundList = materialStoreService.queryMaterialStoreByType(StoreOperateType.IN, calculateDate);
        List<MaterialStockEntity> outboundList = materialStoreService.queryMaterialStoreByType(StoreOperateType.OUT, calculateDate);
        List<MaterialStockEntity> initialList = materialStoreService.queryMaterialStoreByType(StoreOperateType.SURPLUS, preMonteDate);


        //计算和组装需要保持的数据
        List<InsertMaterialStoreParam> insertMaterialStoreParamList = this.calculate(outboundList, initialList, inboundList);


        //保存数据
        materialStoreService.saveMaterialStock(insertMaterialStoreParamList);

        return true;
    }

    /**
     * 对该Code某月数据之后的所有数据进行重新计算
     *
     * @param calculateDate 取计算开始月份
     * @param materialCode  材料编码
     * @return 是否成功
     */
    private boolean calculateAndSave(Date calculateDate, String materialCode) {


        //查询参数
        List<MaterialStockEntity> entityList = materialStoreService.queryMaterialStoreByCode(calculateDate, materialCode);
        Map<String, MaterialStockEntity> entityMap = new HashMap<>();
        entityList.forEach(entity -> {
            entityMap.put(entity.getMaterialCode(), entity);
        });

        List<MaterialStockEntity> outboundList = new ArrayList<>(1);
        MaterialStockEntity outbound = entityMap.get(StoreOperateType.OUT.getType());
        if (outbound != null) {
            outboundList.add(outbound);
        }

        List<MaterialStockEntity> inboundList = new ArrayList<>(1);
        MaterialStockEntity inbound = entityMap.get(StoreOperateType.IN.getType());
        if (inbound != null) {
            inboundList.add(inbound);
        }

        List<MaterialStockEntity> initialList = new ArrayList<>(1);
        MaterialStockEntity surplus = entityMap.get(StoreOperateType.SURPLUS.getType());
        if (surplus != null) {
            initialList.add(surplus);
        }

        //计算和组装需要保持的数据
        List<InsertMaterialStoreParam> insertMaterialStoreParamList = this.calculate(outboundList, initialList, inboundList);


        //保存数据
        materialStoreService.saveMaterialStock(insertMaterialStoreParamList);

        return true;
    }


    /**
     * 根据期初，入库和部分出库信息计算
     *
     * @param outboundList 出库集合
     * @param initialList  期初集合
     * @param inboundList  入库集合
     * @return
     */
    private List<InsertMaterialStoreParam> calculate(List<MaterialStockEntity> outboundList
            , List<MaterialStockEntity> initialList, List<MaterialStockEntity> inboundList) {


        Map<String, MaterialInfo> materialInfoMap = new HashMap<>(initialList.size());

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

        List<InsertMaterialStoreParam> materialInsertParamList = new ArrayList<>(materialInfoMap.size());

        for (Map.Entry<String, MaterialInfo> entry : materialInfoMap.entrySet()) {
            MaterialStockEntity outboundEntity = outboundMap.get(entry.getKey());
            MaterialStockEntity initialEntity = initialMap.get(entry.getKey());
            MaterialStockEntity inboundEntity = inboundMap.get(entry.getKey());

            List<InsertMaterialStoreParam> unitCalculateList
                    = this.unitCalculate(entry.getValue(), outboundEntity, initialEntity, inboundEntity);
            if (!CollectionUtils.isEmpty(unitCalculateList)) {
                materialInsertParamList.addAll(unitCalculateList);
            }
        }

        return materialInsertParamList;
    }


    private List<InsertMaterialStoreParam> unitCalculate(MaterialInfo materialInfo
            , MaterialStockEntity outboundEntity, MaterialStockEntity initialEntity, MaterialStockEntity inboundEntity) {
        List<InsertMaterialStoreParam> materialInsertParamList = new ArrayList<>();

        StoreCalculateBean calculateBean = new StoreCalculateBean(outboundEntity.getNumber(),
                initialEntity.getNumber(), inboundEntity.getNumber());

        UnitMaterialStore outboundUnit = convertUnitMaterialStore(outboundEntity);
        UnitMaterialStore initialUnit = convertUnitMaterialStore(initialEntity);
        UnitMaterialStore inboundUnit = convertUnitMaterialStore(inboundEntity);

        //计算出库
        UnitMaterialStore outboundCalculateResult = calculateBean.calculateOutbound(
                initialUnit, inboundUnit, outboundEntity.getNumber(), outboundEntity.getAmount());
        InsertMaterialStoreParam outboundStoreParam = new InsertMaterialStoreParam(StoreOperateType.OUT, materialInfo, outboundCalculateResult);
        materialInsertParamList.add(outboundStoreParam);

        //计算存余
        UnitMaterialStore surplusBoundCalculateResult = calculateBean.calculateSurplusBound(initialUnit, inboundUnit, outboundUnit);
        InsertMaterialStoreParam surplusBoundStoreParam = new InsertMaterialStoreParam(StoreOperateType.SURPLUS, materialInfo, surplusBoundCalculateResult);
        materialInsertParamList.add(surplusBoundStoreParam);

        return materialInsertParamList;
    }


    private static UnitMaterialStore convertUnitMaterialStore(MaterialStockEntity materialStockEntity) {
        UnitMaterialStore unitMaterialStore = new UnitMaterialStore();
        if (materialStockEntity != null) {
            unitMaterialStore.setMaterialAmount(materialStockEntity.getMaterialAmount());
            unitMaterialStore.setManualAmount(materialStockEntity.getManualAmount());
            unitMaterialStore.setFee(materialStockEntity.getFee());
            unitMaterialStore.setDifferenceAmount(materialStockEntity.getDifferenceAmount());
            unitMaterialStore.setNumber(materialStockEntity.getNumber());
            unitMaterialStore.setAmount(materialStockEntity.getAmount());
        }
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