package com.example.material.service;

import com.example.material.common.ConvertUtil;
import com.example.material.dal.constant.MaterialStatus;
import com.example.material.dal.constant.StoreOperateType;
import com.example.material.dal.entity.MaterialStockEntity;
import com.example.material.dal.repository.MaterialStockRepository;
import com.example.material.service.dto.InsertMaterialStoreParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * MaterialStoreServiceImpl.java
 *
 * @author xbliao   2019/9/24
 */

@Service
public class MaterialStoreServiceImpl implements MaterialStoreService {


    @Resource
    private MaterialStockRepository materialStockRepository;


    public List<MaterialStockEntity> queryMaterialStoreByType(StoreOperateType storeOperateType, Date queryDate) {

        String shortTime = ConvertUtil.convertDateToStr(queryDate);

        List<MaterialStockEntity> inboundMaterialsList =
                materialStockRepository.findGoalDateMaterial(storeOperateType.getType(), MaterialStatus.NORMAL.getStatus(), shortTime);

        return inboundMaterialsList;
    }

    public List<MaterialStockEntity> queryMaterialStore(Date queryDate) {

        String shortTime = ConvertUtil.convertDateToStr(queryDate);

        List<MaterialStockEntity> inboundMaterialsList =
                materialStockRepository.findGoalDateMaterial(null, MaterialStatus.NORMAL.getStatus(), shortTime);

        return inboundMaterialsList;
    }

    @Override
    public boolean saveMaterialStock(StoreOperateType operateType, List<InsertMaterialStoreParam> insertMaterialStoreParamList) {
        //TODO
        return false;

    }


}