package com.zhenxuan.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
public class CheckItemData {

    @JSONField(ordinal = 1)
    private Integer check_item_seq;
    @JSONField(ordinal = 2)
    private Boolean essential_item;
    @JSONField(ordinal = 3)
    private CheckItemDesc check_item_desc;
    @JSONField(ordinal = 4)
    private Integer item_threshold_score;
    @JSONField(ordinal = 5)
    private Integer photo_max_count;
    @JSONField(ordinal = 6)
    private List<String> item_score_list;

    public Integer getCheck_item_seq() {
        return check_item_seq;
    }

    public Boolean getEssential_item() {
        return essential_item;
    }

    public void setEssential_item(Boolean essential_item) {
        this.essential_item = essential_item;
    }

    public void setCheck_item_seq(Integer check_item_seq) {
        this.check_item_seq = check_item_seq;
    }

    public CheckItemDesc getCheck_item_desc() {
        return check_item_desc;
    }

    public void setCheck_item_desc(CheckItemDesc check_item_desc) {
        this.check_item_desc = check_item_desc;
    }

    public Integer getItem_threshold_score() {
        return item_threshold_score;
    }

    public void setItem_threshold_score(Integer item_threshold_score) {
        this.item_threshold_score = item_threshold_score;
    }

    public Integer getPhoto_max_count() {
        return photo_max_count;
    }

    public void setPhoto_max_count(Integer photo_max_count) {
        this.photo_max_count = photo_max_count;
    }

    public List<String> getItem_score_list() {
        return item_score_list;
    }

    public void setItem_score_list(List<String> item_score_list) {
        this.item_score_list = item_score_list;
    }
}
