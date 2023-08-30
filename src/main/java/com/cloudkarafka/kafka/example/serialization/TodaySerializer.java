package com.cloudkarafka.kafka.example.serialization;

import com.cloudkarafka.kafka.example.entity.Today;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/***
 *
 * Serializer class for Today's instances
 *
 */
public class TodaySerializer implements Serializer<Today> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Today today) {
        return (today.getDate() + "," + today.getCounter()).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void close() {

    }
}
