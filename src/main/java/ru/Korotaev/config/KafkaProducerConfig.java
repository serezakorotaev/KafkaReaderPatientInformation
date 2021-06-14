package ru.Korotaev.config;


import org.apache.kafka.clients.producer.ProducerConfig;

import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.Korotaev.Model.UserDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public Map<String, Object> producerConfig(){
     Map<String,Object> props = new HashMap<>();
        String kafkaServer = "localhost:9092";
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
     props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
     props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
     return props;
    }
    @Bean
    public ProducerFactory<Long, UserDto> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
    @Bean
    public KafkaTemplate<Long,UserDto> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
