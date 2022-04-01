package com.hfg.entity;

/**
 * @Author: Zero
 * @Date: 2022/3/29 11:25
 * @Description:
 */
public enum SchoolType {

    MIDDLESCHOOL("中学"),
    PrimarySchool("小学"),
    University("大学");

    private String name;

    SchoolType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
