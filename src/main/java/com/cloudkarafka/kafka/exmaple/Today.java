package com.cloudkarafka.kafka.exmaple;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class Today implements Serializable, Serializer<Today>, Deserializer {

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

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Today today) {
        return (today.getDate() + "," + today.getCounter()).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        String contentAsString = new String(bytes, StandardCharsets.UTF_8);
        String[] segments = contentAsString.split(",");
        System.out.println(contentAsString);
        return new Today(segments[0], Integer.valueOf(segments[1]));
    }


    @Override
    public void close() {

    }
}
