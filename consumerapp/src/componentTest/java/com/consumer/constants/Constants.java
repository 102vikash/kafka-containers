package com.consumer.constants;

//import com.producer.config.AppConstants;

public class Constants {
    public static final String PRODUCE_MESSAGE_URL = "http://localhost:" + "8080" + "/path/send";
    public static final String CONSUMER_GET_BROKER_MESSAGE_URL = "http://localhost:" + "8082" + "/path/getallmessagesfromtopic";
    public static final String MESSAGE = "MESSAGE FROM COMPONENT TEST";
    public static final String ZOOKEEPER_IMAGE_NAME = "wurstmeister/zookeeper:latest";
    public static final String KAFKA_IMAGE_NAME = "wurstmeister/kafka:latest";
    public static final String PRODUCER_APPLICATION_IMAGE_NAME = "producerimage2:latest";
    public static final String CONSUMER_APPLICATION_IMAGE_NAME = "consumerimage2:latest";
    public static final String SUCCESS_PRODUCER_APPLICATION_RESPONSE = "successful";

    public static final String TOPIC_NAME = "component-test";
//    public static final String TOPIC_NAME = AppConstants.TOPIC_NAME;
}
