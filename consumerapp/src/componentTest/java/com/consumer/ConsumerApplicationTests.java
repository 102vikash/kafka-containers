package com.consumer;

import com.consumer.config.ContainerConfig;
import com.consumer.constants.Constants;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;

public class ConsumerApplicationTests {


   // public ConsumerApplicationTests(int x){}

    private static final Network network = Network.newNetwork();

    @Container
    private static final GenericContainer<?> zookeeperContainer =  ContainerConfig.createZookeeperContainer()
            .withNetwork(network);

    @Container
    private static final GenericContainer<?> kafkaContainer = ContainerConfig.createKafkaContainer()
            .withNetwork(network)
            .dependsOn(zookeeperContainer);

    @Container
    private static final GenericContainer<?> consumerContainer = ContainerConfig.createConsumerApplicationContainer()
            .withNetwork(network)
            .dependsOn(kafkaContainer);

    @Container
    private static final GenericContainer<?> producerContainer = ContainerConfig.createProducerApplicationContainer()
            .withNetwork(network)
            .dependsOn(kafkaContainer);

    @Test
    public void shouldAcceptSameMessageAfterPublishedByProducer() {
        producerContainer.start();

        String messageProducedByProducer = Constants.MESSAGE;
        String url = Constants.PRODUCE_MESSAGE_URL +  "/" + messageProducedByProducer;
        TestRestTemplate restTemplate = new TestRestTemplate();
        String response = "";
        try {
           response = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(response, Constants.SUCCESS_PRODUCER_APPLICATION_RESPONSE);

        consumerContainer.start();
        await().pollInterval(Duration.ofSeconds(4)).atMost(6, TimeUnit.SECONDS).untilAsserted(()->{

        });
        url = Constants.CONSUMER_GET_BROKER_MESSAGE_URL;
        String messageConsumedByConsumer = restTemplate.getForObject(url, String.class);

        System.out.println("message is :" + messageConsumedByConsumer);
        assertEquals(messageConsumedByConsumer, messageProducedByProducer);

    }

}
