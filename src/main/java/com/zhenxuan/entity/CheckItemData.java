package com.zhenxuan.entity;

import java.util.List;
import java.util.Map;

public class CheckItemData {

/*    {
        "check_item_seq": 1,
            "check_item_desc": {
        "en": "",
                "zh": "有独立排风设备,以保证通风."
    },
        "item_threshold_score": 0,
            "photo_max_count": 3,
            "item_score_list": [
        "N/A",
                0,
                1
                        ]
    },*/

    private Integer check_item_seq;
    private CheckItemDesc check_item_desc;
    private Integer item_threshold_score;
    private Integer photo_max_count;
    private List<String> item_score_list;

    public Integer getCheck_item_seq() {
        return check_item_seq;
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
