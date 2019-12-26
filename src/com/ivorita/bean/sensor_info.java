package com.ivorita.bean;

public class sensor_info {

    private int id;

    private float temperature;

    private float humidity;

    private float illumination;

    private float gas;

    private int illuex;

    public sensor_info(int id, float temperature, float humidity, float gas, float illumination,int illuex) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.gas = gas;
        this.illumination = illumination;
        this.illuex = illuex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getIllumination() {
        return illumination;
    }

    public void setIllumination(float illumination) {
        this.illumination = illumination;
    }

    public float getGas() {
        return gas;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }

    public int getIlluex() {
        return illuex;
    }

    public void setIlluex(int illuex) {
        this.illuex = illuex;
    }
}
