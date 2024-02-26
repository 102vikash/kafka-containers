package com.consumer.config;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import com.consumer.constants.Constants;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class ContainerConfig {
    public static GenericContainer<?> createKafkaContainer() {
        return new GenericContainer<>(DockerImageName.parse(Constants.KAFKA_IMAGE_NAME))
                .withNetworkAliases("kafka")
                .withExposedPorts(9092)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        cmd.getHostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(9092), new ExposedPort(9092))))
                )
                .withEnv("KAFKA_ADVERTISED_HOST_NAME", "localhost")
                .withEnv("KAFKA_ZOOKEEPER_CONNECT", "zookeeper:2181")
                .withEnv("KAFKA_ADVERTISED_LISTENERS", "INSIDE://kafka:9092,OUTSIDE://docker:9094")
                .withEnv("KAFKA_LISTENERS", "INSIDE://:9092,OUTSIDE://:9094")
                .withEnv("KAFKA_LISTENER_SECURITY_PROTOCOL_MAP", "INSIDE:PLAINTEXT,OUTSIDE:PLAINTEXT")
                .withEnv("KAFKA_INTER_BROKER_LISTENER_NAME", "INSIDE")
                .withEnv("LOG4J_LOGGER_KAFKA", "ERROR")
                .withEnv("LOG4J_LOGGER_ORG_APACHE_KAFKA", "ERROR")
                .withEnv("KAFKA_CREATE_TOPICS", Constants.TOPIC_NAME)
                .waitingFor(Wait.forListeningPort());
    }


    public static GenericContainer<?> createZookeeperContainer() {
        return new GenericContainer<>(DockerImageName.parse(Constants.ZOOKEEPER_IMAGE_NAME))
                .withNetworkAliases("zookeeper")
                .withExposedPorts(2181)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        cmd.getHostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(2181), new ExposedPort(2181))))
                )
                .waitingFor(Wait.forListeningPort());
    }

    public static GenericContainer<?> createProducerApplicationContainer() {
        return new GenericContainer<>(DockerImageName.parse(Constants.PRODUCER_APPLICATION_IMAGE_NAME))
                .withNetworkAliases("producerapp")
                .withExposedPorts(8080)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        cmd.getHostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(8080), new ExposedPort(8080))))
                )
                .waitingFor(Wait.forListeningPort());
    }

    public static GenericContainer<?> createConsumerApplicationContainer() {
        return new GenericContainer<>(DockerImageName.parse(Constants.CONSUMER_APPLICATION_IMAGE_NAME))
                .withNetworkAliases("consumerapp")
                .withExposedPorts(8082)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        cmd.getHostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(8082), new ExposedPort(8082))))
                )
                .waitingFor(Wait.forListeningPort());
    }
}
