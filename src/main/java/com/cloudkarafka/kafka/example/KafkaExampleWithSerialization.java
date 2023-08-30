package com.cloudkarafka.kafka.example;


import com.cloudkarafka.kafka.example.entity.Today;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class KafkaExampleWithSerialization {

    public void produce(Properties props,
                        String topic)
    {
        Thread one = new Thread(() -> {
                try {
                    Producer<String, Today> producer = new KafkaProducer<>(props);
                    int i = 0;
                    while(true) {
                        Today today = new Today(new Date().toString(),i);
                        producer.send(new ProducerRecord<>(topic, Integer.toString(i), today));
                        Thread.sleep(1000);
                        i++;
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
        KafkaConsumer<String, Today> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        while (true){
            ConsumerRecords<String, Today> records = consumer.poll(1000);
            for(ConsumerRecord<String, Today> record : records)
                System.out.printf("%s [%d] offset=%d, key=%s, value=\"%s\"\n",
                        record.topic(), record.partition(),
                        record.offset(), record.key(), record.value().getDate());
        }
    }
}
