package com.example.material.dal.constant;

/**
 * MaterialStatus.java
 *
 * @author xbliao   2019/9/24
 */
public enum MaterialStatus {


    NORMAL("normal", "正常"),
    DELETE("delete", "删除"),
    ;


    private String status;

    private String desc;

    MaterialStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}