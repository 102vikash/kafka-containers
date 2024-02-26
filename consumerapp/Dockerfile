FROM docker-registry-ro.lab.nordigy.ru:443/platform/java:ol7s-openjdk-11
EXPOSE 8082
ADD build/libs/kafkaconsumer.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]