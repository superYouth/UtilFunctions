package com.zhenxuan.entity;

import java.util.List;

public class AuditItemConfig {

    private String audit_item_name;
    private List<CheckGroupData> check_group_data;

    public String getAudit_item_name() {
        return audit_item_name;
    }

    public void setAudit_item_name(String audit_item_name) {
        this.audit_item_name = audit_item_name;
    }

    public List getCheck_group_data() {
        return check_group_data;
    }

    public void setCheck_group_data(List<CheckGroupData> check_group_data) {
        this.check_group_data = check_group_data;
    }
}
