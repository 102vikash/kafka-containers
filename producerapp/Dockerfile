FROM docker-registry-ro.lab.nordigy.ru:443/platform/java:ol7s-openjdk-11
EXPOSE 8080
ADD build/libs/kafkaproducer.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]