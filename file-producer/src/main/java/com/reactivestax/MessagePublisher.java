package com.reactivestax;

import com.reactivestax.avro.PersonalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessagePublisher {
    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(PersonalDetails orderEvent) {
        log.info("sending Order Id Event {}",.getOrderId());
        kafkaTemplate.send(topic, orderEvent.getOrderId(), orderEvent);
    }
}

