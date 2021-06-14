package ru.Korotaev;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * This is consumer class
 * msgListener - method where giving topic
 */
@EnableKafka
@SpringBootApplication
public class SimpleKafkaExampleApplication {

    @KafkaListener(topics = "patientDescription")
    public void msgListener(ConsumerRecord record){
        System.out.println(record.partition());
        System.out.println(record.key());
        System.out.println(record.value());
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleKafkaExampleApplication.class,args);
    }
}
