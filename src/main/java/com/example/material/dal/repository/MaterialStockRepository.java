package com.example.material.dal.repository;

import com.example.material.dal.entity.MaterialStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * MaterialStockRepository.java
 *
 * @author xbliao   2019/9/24
 */
public interface MaterialStockRepository extends JpaRepository<MaterialStockEntity, String> {


    //查询这个时间之前所有材料入库
    @Query(nativeQuery = true, value =
            "select * from person_test.material_stock where type = :type and status = :status and short_time = :shortTime")
    List<MaterialStockEntity> findGoalDateMaterial(@Param("type") String type,
                                                   @Param("status") String status,
                                                   @Param("goalDate") String shortTime);



}