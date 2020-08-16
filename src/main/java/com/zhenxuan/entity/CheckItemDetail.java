package com.zhenxuan.entity;

public class CheckItemDetail {

    private Integer groupSeq;
    private Integer checkItemSeq;
    private String desc = "";

    public Integer getGroupSeq() {
        return groupSeq;
    }

    public void setGroupSeq(Integer groupSeq) {
        this.groupSeq = groupSeq;
    }

    public Integer getCheckItemSeq() {
        return checkItemSeq;
    }

    public void setCheckItemSeq(Integer checkItemSeq) {
        this.checkItemSeq = checkItemSeq;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CheckItemDetail{" +
                "groupSeq=" + groupSeq +
                ", checkItemSeq=" + checkItemSeq +
                ", desc='" + desc + '\'' +
                '}';
    }
}
