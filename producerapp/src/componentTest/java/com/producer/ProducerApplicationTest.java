package com.producer;

import com.producer.Config.ContainerConfig;
import com.producer.Constants.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProducerApplicationTest {
    private static final Network network = Network.newNetwork();
    @Container
    private static final GenericContainer<?> zookeeperContainer =  ContainerConfig.createZookeeperContainer()
            .withNetwork(network);

    @Container
    private static final GenericContainer<?> kafkaContainer = ContainerConfig.createKafkaContainer()
            .withNetwork(network)
            .dependsOn(zookeeperContainer);

    @Container
    private static final GenericContainer<?> producerContainer = ContainerConfig.createProducerApplicationContainer()
            .withNetwork(network)
            .dependsOn(kafkaContainer);

    @Test
    void isConnectingToKafka() {
        producerContainer.start();
        String url = Constants.PRODUCE_MESSAGE_URL + "/" + Constants.MESSAGE;
        String response = "";
        try {
            TestRestTemplate restTemplate = new TestRestTemplate();
            response = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("message is: " + producerContainer.getLogs());
        assertEquals(response, Constants.SUCCESS_PRODUCER_APPLICATION_RESPONSE);

    }
}
