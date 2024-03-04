# Golden raspberry awards
Possibilitar a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards


### Pré-requisitos
Antes de começar, certifique-se de ter atendido aos seguintes requisitos:

- Java JDK (21)

# Testar e compilar projeto
 - ./mvnw clean install -> na raiz do projeto para compilar a aplicação e rodar os testes.
 - ./mvnw test -> roda somente os testes

# Rodar a aplicação
 - na raiz do projeto usar o seguinte comando ->  java -jar target/golden-raspberry-awards-0.0.1-SNAPSHOT.jar


# Acesso ao swagger
http://localhost:8080/swagger-ui/index.html

# Acesso a base h2
 - http://localhost:8080/h2-console/login.jsp
 - username - sa
 - senha = sa

### Api -> produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido
 - GET -> http://localhost:8080/api/golder-rapsbery-awards/v1