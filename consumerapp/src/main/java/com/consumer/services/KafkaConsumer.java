package com.consumer.services;
import com.consumer.config.Constants;
import com.consumer.controllers.GetMessagesEndPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = Constants.TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message){
        GetMessagesEndPoint.message += message;
        LOGGER.info(String.format("Message received -> %s", message));
    }
}