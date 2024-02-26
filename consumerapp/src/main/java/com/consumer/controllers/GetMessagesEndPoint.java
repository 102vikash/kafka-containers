package com.consumer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path")
public class GetMessagesEndPoint {

    public static String message = "";
    @GetMapping("/getallmessagesfromtopic")
    public ResponseEntity<?> updateLocation0() {
            return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
