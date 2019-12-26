package com.ivorita.bean;

import java.util.List;

public class sensor_infoTotal {

    private int total;//信息数量
    private List<sensor_info> rows;//信息列表

    public sensor_infoTotal(int total, List<sensor_info> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<sensor_info> getRows() {
        return rows;
    }

    public void setRows(List<sensor_info> rows) {
        this.rows = rows;
    }
}
