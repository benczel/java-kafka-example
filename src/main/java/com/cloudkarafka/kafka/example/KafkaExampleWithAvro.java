package com.cloudkarafka.kafka.example;

import com.cloudkarafka.kafka.example.entity.Employee;
import com.cloudkarafka.kafka.example.util.RandomString;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Random;

import java.util.Properties;

public class KafkaExampleWithAvro {

    public void produce(Properties props,
                        String topic)
    {
        Thread one = new Thread(() -> {
            KafkaProducer<String, SpecificRecord> producer = new KafkaProducer<>(props);
            int i = 0;

            try {
                while(true){
                    Employee employee = Employee.newBuilder().setId(new Random().nextInt())
                            .setFirstname(RandomString.getAlphaNumericString(10))
                            .setLastname(RandomString.getAlphaNumericString(10))
                            .build();
                    producer.send(new ProducerRecord<>(topic, Integer.toString(i), employee));
                    Thread.sleep(1000);
                    i++;
                }
            }catch (InterruptedException e){
                System.out.println(e);
            }

        });
        one.start();
    }

    public void consume(Properties props,
                        String topic)
    {
        KafkaConsumer<String, Employee> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        while (true){
            ConsumerRecords<String, Employee> records = consumer.poll(1000);
            for(ConsumerRecord<String, Employee> record: records){
                System.out.printf("%s [%d] offset=%d, key=%s, value=\"%s\"\n",
                        record.topic(), record.partition(),
                        record.offset(), record.key(), record.value().toString()
                );
            }
        }
    }
}
