Projeto de Empréstimos

Este projeto implementa um serviço para determinar quais modalidades de empréstimo uma pessoa tem acesso, baseado em critérios específicos como idade, salário e localização.

Modalidades de Empréstimo
As modalidades de empréstimo disponíveis são:

Empréstimo pessoal: Taxa de juros de 4%.

Empréstimo consignado: Taxa de juros de 2%.

Empréstimo com garantia: Taxa de juros de 3%.

Exemplo de Uso
Para determinar as modalidades de empréstimo disponíveis para um cliente, envie uma requisição POST para {{host}}/customer-loans com os seguintes dados:

json

{
  "age": 26,
  "cpf": "275.484.389-23",
  "name": "Vuxaywua Zukiagou",
  "income": 7000.00,
  "location": "SP"
}

A resposta será um JSON contendo o nome do cliente e a lista de empréstimos disponíveis com seus respectivos tipos e taxas de juros:

json

{
  "customer": "Vuxaywua Zukiagou",
  "loans": [
    {
      "type": "PERSONAL",
      "interest_rate": 4
    },
    {
      "type": "GUARANTEED",
      "interest_rate": 3
    },
    {
      "type": "CONSIGNMENT",
      "interest_rate": 2
    }
  ]
}

Requisitos Implementados:

* Concede o empréstimo pessoal se o salário do cliente for igual ou inferior a R$ 3000.
* Concede o empréstimo pessoal se o salário do cliente estiver entre R$ 3000 e R$ 5000, se o cliente tiver menos de 30 anos e residir em São Paulo (SP).
* Concede o empréstimo consignado se o salário do cliente for igual ou superior a R$ 5000.
* Concede o empréstimo com garantia se o salário do cliente for igual ou inferior a R$ 3000.
* Concede o empréstimo com garantia se o salário do cliente estiver entre R$ 3000 e R$ 5000, se o cliente tiver menos de 30 anos e residir em São Paulo (SP).

Requirements:

Install Java 17.

Install Git.

Install Maven.

Install Docker Compose (or PostgreSQL*).

Configuração do Projeto

O projeto está sendo executato com o Java 17

A aplicação também conta com a ferramenta Swagger para documentação. Consulte https://swagger.io/ para mais informações.

Configuração do Spring Boot
O projeto utiliza Spring Boot com a seguinte configuração no arquivo application.yml:

spring:
  application:
    name: loans
  datasource:
    url: jdbc:postgresql://localhost:5432/db-loans
    username: postgres
    password: 123456
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        
Docker Compose
Para executar o projeto com PostgreSQL usando Docker Compose, utilize o seguinte docker-compose.yml:

version: '3.9'

services:
  postgres:
    image: postgres
    ports:
      - 5432:5432
    volumes:
      - ~/apps/postgres:/var/lib/postgresql/data
    environment:
      - POSTGRES_DB=db-loans
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=123456

Executanto o Projeto

1. Clone o repositório usando o SSH ou HTTPS:
   
    git clone git@github.com:Guilherme-oliver/Desafio-Empr-stimos.git

    git clone https://github.com/Guilherme-oliver/Desafio-Empr-stimos.git

2. Importe o porjeto no programa de sua preferência

3. Execute o Docker Compose para iniciar o PostgreSQL:

    docker-compose up -d

4. Execute a aplicação

5. Contribuição: Sinta-se à vontade para contribuir com o projeto através de pull requests. Para grandes mudanças, abra uma issue primeiro para discutir o que você gostaria de mudar.
