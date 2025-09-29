# Desafio Santander OSA!

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)](https://shields.io/)
[![Linguagens](https://img.shields.io/badge/Linguagens-Java%20%7C%20Angular-blue)](https://www.oracle.com/java/)

## Descrição

Uma aplicação web para gerenciamento de transações bancárias, desenvolvida como parte do Desafio Santander OSA! A aplicação permite aos usuários registrar uma conta, depositar, sacar e visualizar o histórico de transações.

## Funcionalidades

*   **Autenticação:**
    *   Registro de novos usuários
    *   Login com nome de usuário e senha
*   **Gerenciamento de Contas:**
    *   Visualização do saldo atual
    *   Depósito de fundos
    *   Saque de fundos
    *   Visualização do histórico de transações

## Tecnologias Utilizadas

*   **Back-end:**
    *   Java
    *   Spring Boot
    *   Spring Security (JWT)
    *   Spring Data JPA
    *   PostgreSQL
    *   Redis (para cache)
    *   Flyway (para migrações de banco de dados)


## Pré-requisitos

*   Java 21 ou superior
*   Docker (opcional, para executar os serviços em containers)

## Configuração e Execução

1.  **Back-end (Spring Boot):**

    *   Clone o repositório:
    ```bash
    git clone <url-do-repositorio>
    cd desafio-osa-backend
    ```

    *   Configure as variáveis de ambiente no `application.properties` ou `application.yml`.

        Exemplo:
        ```properties
                spring.datasource.url=jdbc:postgresql://localhost:5432/desafioosa_osa
                spring.datasource.username=postgres
                spring.datasource.password=postgres
                spring.jpa.hibernate.ddl-auto=update
                spring.jpa.show-sql=true
                spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
                spring.data.redis.host=localhost
                spring.data.redis.port=6379
        ```

    *   Execute a aplicação:
    ```bash
    ./mvnw spring-boot:run # Maven
    ./gradlew bootRun  # Gradle
    ```


## Execução com Docker Compose (Opcional)

1.  Certifique-se de ter o Docker e o Docker Compose instalados.
2.  Na raiz do projeto, execute:
    ```bash
    docker build -t desafioosa .
  docker run -e SPRING_PROFILES_ACTIVE=prod -d -p 8080:8080 --name desafioosa_container desafioosa
    ```
    A aplicação estará acessível em `http://localhost:4200`.

## Próximos Passos

*   Implementar testes unitários e de integração
*   Melhorar a estilização da aplicação
*   Implementar autorização (controle de acesso baseado em roles)
*   Adicionar paginação ao histórico de transações

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## Contato

Se tiver alguma dúvida ou sugestão, entre em contato:

[fabio] - [fabio.jdluz@gmail.com]
[[Link para seu LinkedIn ou outro perfil](https://www.linkedin.com/in/fabiojdluz/)]
