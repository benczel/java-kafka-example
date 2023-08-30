package com.cloudkarafka.kafka.example;

import com.cloudkarafka.kafka.example.configuration.KafkaClusterConfiguration;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

import java.util.Properties;

public class KafkaExampleWithAvroMain {

    public static  void main(String[] args){
        String broker = System.getenv("CLOUDKARAFKA_BROKERS");
        String username = System.getenv("CLOUDKARAFKA_USERNAME");
        String password = System.getenv("CLOUDKARAFKA_PASSWORD");
        String topic = username + "-"+"default";
        KafkaClusterConfiguration clusterConfiguration = new KafkaClusterConfiguration(
                broker,
                username,
                password
        );

        KafkaExampleWithAvro kafkaExampleWithAvro = new KafkaExampleWithAvro();
        Properties props = clusterConfiguration.getProperties();
        props.put("schema.registry.url", "https://schemaregistry.cloudkarafka.com");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", KafkaAvroSerializer.class.getName());
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", KafkaAvroDeserializer.class.getName());
        kafkaExampleWithAvro.produce(props, topic);
        kafkaExampleWithAvro.consume(props, topic);
    }
}