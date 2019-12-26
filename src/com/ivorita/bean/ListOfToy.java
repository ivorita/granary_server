package com.ivorita.bean;

import java.util.List;

public class ListOfToy {

    private int total;

    private List<ToysPic> toysPics;

    public ListOfToy(int total, List<ToysPic> toysPics) {
        this.total = total;
        this.toysPics = toysPics;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ToysPic> getToysPics() {
        return toysPics;
    }

    public void setToysPics(List<ToysPic> toysPics) {
        this.toysPics = toysPics;
    }

}
