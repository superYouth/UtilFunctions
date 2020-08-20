package com.zhenxuan.entity;

import java.util.List;

public class CheckGroupData {

    private Integer check_group_seq;
    private String check_group_type;
    private List<CheckItemData> check_item_data;

    public Integer getCheck_group_seq() {
        return check_group_seq;
    }

    public void setCheck_group_seq(Integer check_group_seq) {
        this.check_group_seq = check_group_seq;
    }

    public String getCheck_group_type() {
        return check_group_type;
    }

    public void setCheck_group_type(String check_group_type) {
        this.check_group_type = check_group_type;
    }

    public List<CheckItemData> getCheck_item_data() {
        return check_item_data;
    }

    public void setCheck_item_data(List<CheckItemData> check_item_data) {
        this.check_item_data = check_item_data;
    }
}
