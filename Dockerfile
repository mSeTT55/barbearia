FROM openjdk:22
WORKDIR /barbearia
COPY /target/barbearia-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]