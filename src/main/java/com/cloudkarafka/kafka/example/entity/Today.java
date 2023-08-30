package com.cloudkarafka.kafka.exmaple.entity;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;



public class Today implements Serializable {

    private String date;
    private int counter;

    public Today(){

    }
    public Today(String date, int counter){
        this.date = date;
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
