package com.cloudkarafka.kafka.exmaple;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class KafkaExample {

    public void produce(Properties props,
                        String topic)
    {
        Thread one = new Thread(() -> {
                try {
                    Producer<String, String> producer = new KafkaProducer<>(props);
                    while(true) {
                        Date d = new Date();
                        producer.send(new ProducerRecord<>(topic, d.toString()));
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e){
                    System.out.println(e);
                }
        });
        one.start();
    }

    public void consume(Properties props,
                        String topic
                        )
    {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        while (true){
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for(ConsumerRecord<String, String> record : records)
                System.out.printf("%s [%d] offset=%d, key=%s, value=\"%s\"\n",
                        record.topic(), record.partition(),
                        record.offset(), record.key(), record.value());
        }
    }
}
