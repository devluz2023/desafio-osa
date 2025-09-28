FROM eclipse-temurin:21-jdk AS builder

# Instalar Maven (necessário)
RUN apt-get update && \
    apt-get install -y maven

# Define o diretório de trabalho
WORKDIR /app

# Copie o pom.xml e o código fonte
COPY pom.xml .
COPY src ./src

# Construa a aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Construção da imagem final
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho
WORKDIR /app
# Copie o jar da etapa de construção

COPY --from=builder /app/target/desafioosa-0.0.1-SNAPSHOT.jar.original app.jar

# Comando para executar o jar
ENTRYPOINT ["java", "-jar", "app.jar"]
