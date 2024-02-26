package com.producer.services;
import com.producer.config.AppConstants;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public boolean sendMessageToBroker(String message) {
        try{
            this.kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}