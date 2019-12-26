package com.ivorita.bean;

public class SensorStatus {

    private int id;

    private int led_status;

    public SensorStatus(int id, int led_status) {
        this.id = id;
        this.led_status = led_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLed_status() {
        return led_status;
    }

    public void setLed_status(int led_status) {
        this.led_status = led_status;
    }
}
