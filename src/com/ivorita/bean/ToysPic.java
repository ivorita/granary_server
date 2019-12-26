package com.ivorita.bean;

public class ToysPic {

    public ToysPic(String url, int num) {
        this.url = url;
        this.num = num;
    }

    private String url;

    private int num;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
