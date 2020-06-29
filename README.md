# POC de CRUD de Clientes com Spring Boot
Instruções de uso

## Inicialização do projeto

### Opção 1: IDE
Executar a classe **br.com.kenoby.poccliente.PocClienteSpringApplication** na IDE

### Opção 2: Terminal
Na pasta do projeto, executar `mvn package` e, depois, `java -jar target/poc-cliente-spring-0.0.1.jar`.
No profile padrão a aplicação usa um banco de dados H2 embarado em memória.

Caso queria acessar o banco que fica no Azure, solicite o uso do profile **prod**, fazendo `java -jar -Dspring.profiles.active=prod target/poc-cliente-spring-0.0.1.jar`.

## Experimentando no projeto
Independente de como executou (base H2 em memória ou banco na nuvem), a aplicação já inicia com 7 registros de clientes.

Você pode importar o arquivo `poc-cliente-spring.postman_collection.json` na raiz do projeto no Postman e experimentar todos os Endpoints

Se executar o profile padrão que usa um banco H2 em memória, pode acessar a base de dados via browser em http://localhost:8321/h2-console.
As configurações de acesso são:

  *  **Driver Class** - org.h2.Driver *(provavelmente já estará assim)*
  
  *  **JDBC URL** - jdbc:h2:mem:poccliente
  
  *  **User name** - sa  *(provavelmente já estará assim)*
  
  *  **Password** - [vazio] *(provavelmente já estará assim)*

## Documentação
  Basta acessar o Swagger da API em http://localhost:8321/swagger-ui.html
