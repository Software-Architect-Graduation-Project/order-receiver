FROM openjdk:11.0.10

WORKDIR /app
COPY ./build/libs .

ENTRYPOINT ["sh", "-c", "java -jar order-receiver-1.0.0.jar"]