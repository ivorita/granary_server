package com.ivorita.bean;

import java.util.List;
import java.util.Map;

public class InfoTotalTemplate {

    private int total;//信息数量
    private List<Map<String, Object>> rows;//信息列表

    public InfoTotalTemplate(int total, List<Map<String, Object>> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
