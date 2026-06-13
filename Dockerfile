FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8085
CMD ["java", "-jar", "target/nfse-api-1.0.0.jar"]
