
# Etapa 2: Construção da imagem final
FROM eclipse-temurin:21-jdk



VOLUME /tmp
ARG JAR_FILE=target/desafioosa-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=prod 
ENTRYPOINT ["java","-jar","/app.jar"]