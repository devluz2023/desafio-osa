# Etapa 1: Construção
FROM maven:3.8.4-openjdk-21-slim AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e o código-fonte
COPY pom.xml .
COPY src ./src

# Compile a aplicação e crie o jar
RUN mvn clean package -DskipTests

# Etapa 2: Execução
FROM eclipse-temurin:21-jdk AS builder

# Defina o diretório de trabalho na imagem final
WORKDIR /app

# Copie o jar da etapa de construção

COPY --from=builder /app/target/desafioosa-0.0.1-SNAPSHOT.jar.original app.jar

# Comando para executar o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
