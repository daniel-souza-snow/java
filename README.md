# java-crud-address

Author: DANIEL SOUZA DE OLIVEIRA
Tech Stack: Java 11, Spring Boot, JPA, HIBERNATE Tipo: TESTE-JAVA

## 1 - Realizado o build do projeto
A execução do build do projeto é necessária para gerar os binários da aplicação,

## 2 - Execução do Projeto
### criar imagem do container
na raiz do projeto executes os comandos:   
./mvnw clean package -DskipTests
docker build -t {nome_imagem}:v1 .
### criar container com base na imagem Docker
docker run -p 8080:8080 {nome_imagem}
### para executar o projeto sem o Docker instalado na maquina
Caso não posssua o Docker vá até a classe principal e execute-a pela sua IDE
   
## 3 - Acessar a plicação 
O projeto esta Configurado para rodar no TOMCAT e sua porta padrão é 8080, isso não foi modificado, 
por tanto o acesso a aplicação vai ser feita por ela.
Exemplo: http://localhost:8080/swagger-ui.html
# Estrutura dos pacotes de fontes da aplicação

Abaixo segue um resumo da estrutura dos fontes nesta aplicação.

<pre>
        ├── JavaApplication.java
        ├── config
        │   ├── AppConfig.java
        │   ├── MessageConfig.java
        │   └── SwaggerConfig.java
        ├── controller
        │   ├── AdressController.java
        │   ├── AppControllerAdvice.java
        │   └── errors
        │       └── ApiErrors.java
        ├── exceptions
        │   └── BusinessRuleException.java
        ├── model
        │   └── Adress.java
        ├── repository
        │   └── AdressRepository.java
        └── service
            └── AdressService.java
</pre>