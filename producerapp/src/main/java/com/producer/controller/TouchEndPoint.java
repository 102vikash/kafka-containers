package com.producer.controller;

import com.producer.config.AppConstants;
import com.producer.services.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/path")
public class TouchEndPoint {

        @Autowired
        private KafkaService kafkaService;

        @GetMapping("/send/{message}")
        public ResponseEntity<?> publishMessage(@PathVariable String message) {

            boolean flag = this.kafkaService.sendMessageToBroker(message);
            if(flag == true)
            return new ResponseEntity<>(AppConstants.SUCCESS_RESPONSE_MESSAGE, HttpStatus.OK);
            else return new ResponseEntity<>(AppConstants.UNSUCCESS_RESPONSE_MESSAGE, HttpStatus.EXPECTATION_FAILED);
        }


    }
