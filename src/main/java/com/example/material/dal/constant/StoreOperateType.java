package com.example.material.dal.constant;

/**
 * StoreOperateType.java
 *
 * @author xbliao   2019/9/24
 */
public enum StoreOperateType {

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


    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}