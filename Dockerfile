FROM openjdk:8
ADD target/transaction-service.jar transaction-service.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","transaction-service.jar"]