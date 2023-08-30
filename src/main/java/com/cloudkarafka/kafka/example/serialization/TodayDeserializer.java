package com.cloudkarafka.kafka.exmaple.serialization;

import com.cloudkarafka.kafka.exmaple.entity.Today;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 * Deserializer class for Today's instances
 *
 */
public class TodayDeserializer implements Deserializer<Today> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Today deserialize(String s, byte[] bytes) {
        String contentAsString = new String(bytes, StandardCharsets.UTF_8);
        String[] segments = contentAsString.split(",");
        return new Today(segments[0], Integer.valueOf(segments[1]));
    }

    @Override
    public void close() {

    }
}
