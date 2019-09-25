package com.example.material.service;

import com.example.material.dal.constant.StoreOperateType;
import com.example.material.dal.entity.MaterialStockEntity;
import com.example.material.service.dto.InsertMaterialStoreParam;

import java.util.Date;
import java.util.List;

/**
 * MaterialCalculateService.java
 *
 * @author xbliao   2019/9/24
 */


public interface MaterialStoreService {


    public List<MaterialStockEntity> queryMaterialStoreByType(StoreOperateType storeOperateType, Date queryDate);


    public List<MaterialStockEntity> queryMaterialStore(Date queryDate);


    public List<MaterialStockEntity> queryMaterialStoreByCode(Date queryDate, String materialCode);


    public boolean saveMaterialStock(List<InsertMaterialStoreParam> insertMaterialStoreParamList);


}