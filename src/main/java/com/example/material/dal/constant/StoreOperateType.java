package com.example.material.dal.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * StoreOperateType.java
 *
 * @author xbliao   2019/9/24
 */
public enum StoreOperateType {

    NULL("", ""),
    IN("in", "入库"),
    OUT("out", "出库"),
    SURPLUS("surplus", "存余"),

    ;

    private String type;

    private String desc;

    StoreOperateType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private final static Map<String, StoreOperateType> typeMap = new HashMap<>(values().length);

    static {
        for (StoreOperateType operateType : values()) {
            typeMap.put(operateType.type, operateType);
        }
    }


    public static StoreOperateType convert(String typeStr) {
        StoreOperateType type = typeMap.get(typeStr);
        //默认为正常审核模式
        if (type == null) {
            return NULL;
        }
        return type;

    }


    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}