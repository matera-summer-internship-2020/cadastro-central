# Central register 🇺🇸

## About
Central register is composed by REST APIs, `Client`, `Address`, `IdentityDocument` and `Telephone`. They are organized as shown in the following image, and its purpose is to register people, as `Client`, by the attributes: `Name`, `MaritalStatus`, `Password`, list of `IdentityDocument`, list of `Telephone` and `Address`.

[![Central Register Schema](https://i.postimg.cc/25pvY3Rs/central-register-updated.png)](https://postimg.cc/75VbMHL9)
## First steps
### Database
Before running our application, you have to create a postgres database locally with the following attributes:
#### Server
* Host: `localhost`
* Port: `5432`
* Database name: `central_register`
#### Authentication
* Authentication: `Database Native`
* Username: `postgres`
* Password: `postgres`

Also, we used the [Lombok library](https://projectlombok.org/) in the project, so if you open it in an IDE, like IntelliJ, you should install a plugin to run the application without errors.

With the created base, you just need to run the application, and it will create the tables by itself because we are using [Liquibase library](https://www.liquibase.org/). After that you can access our documentation as shown below.  

## Documentation
You should be able to access the project documentation locally at http://localhost:8080/swagger-ui.html while application is running.


# Cadastro central 🇧🇷
## Sobre
Cadastro Central é composto por APIs REST, `Client`, `Address`, `IdentityDocument` e `Telephone`. São organizados como mostra na imagem abaixo, e seu propósito é cadastrar pessoas, como `Client`, pelos atributos: `Name`, `MaritalStatus`, `Password`, lista de `IdentityDocument`, lista de `Telephone` e `Address`.

[![Esquema de Cadastro Central](https://i.postimg.cc/25pvY3Rs/central-register-updated.png)](https://postimg.cc/75VbMHL9)
## Primeiros passos
### Banco da dados
Antes de rodar a aplicação, você tem que criar um banco de dados postgres localmente com os seguintes atributos:
#### Servidor
* Host: `localhost`
* Port: `5432`
* Nome do banco: `central_register`
#### Autenticação
* Autenticação: `Database Native`
* Usuário: `postgres`
* Senha: `postgres`

Nós usamos a [biblioteca Lombok](https://projectlombok.org/) neste projeto, então caso for abri-lo em uma IDE, como IntelliJ, você deve instalar um plugin para conseguir rodar a aplicação sem erros.

Com o banco criado, você só precisa rodar a aplicação, e ela criará as tabelas sozinha porque estamos usando a [biblioteca do Liquibase](https://www.liquibase.org/). Feito isso, você pode acessar a nossa documentação como mostrado abaixo.  

## Documentação
Você pode acessar a documentação do projeto localmente via http://localhost:8080/swagger-ui.html enquanto roda a aplicação.


