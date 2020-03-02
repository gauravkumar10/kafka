package com.reactivestax;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
@ConfigurationProperties(prefix = "kafka.publisher")
@Configuration
@Getter
@Setter
public class KafkaFileProducer {
    public static final String SCHEMA_REGISTRY_URL = "schema.registry.url";
    private long bufferMemory;
    private int lingerTimeInMs;
    private int publishBatchSize;
    private int retryCount;
    private String bootstrapServers;
    private String schemaRegistry;

    public KafkaFileProducer(String topicName) {
    }

    public Map<String, Object> publisherProps() {
        final Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retryCount);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, publishBatchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerTimeInMs);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(SCHEMA_REGISTRY_URL, schemaRegistry);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(publisherProps());
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    public void sendMessage(String s, String line) {

    }
}
