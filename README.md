# RESTful API de aplicativo e-commerce

> Backend de uma aplicação e-commerce, com Java e Spring Framework. Frontend da aplicação disponível [aqui](https://github.com/eduardorcury/e-commerce-app-frontend).

## :wrench: &nbsp;&nbsp; Ferramentas utilizadas

- Java.
- Criação de RESTful API com [Spring Boot](https://github.com/spring-projects/spring-boot).
- Persistência dos dados com [Hibernate](https://hibernate.org/) e [Spring JPA](https://github.com/spring-projects/spring-data-jpa).
- Autenticação e autorização com [Spring Security](https://github.com/spring-projects/spring-security).
- Tokens [JWT](https://jwt.io/).
- Armazenamento de imagens com [Amazon S3](https://aws.amazon.com/pt/s3/).
- Banco de dados [MySQL](https://github.com/mysql).
- Deploy da aplicação com [Heroku](https://www.heroku.com/).

## :bulb: &nbsp;&nbsp; Funcionalidades

### Classes de domínio

A aplicação possibilita que sejam armazenados clientes, cada qual com uma coleção de telefones e endereços associados. O cliente pode solicitar pedidos de produtos, associados a uma ou mais categorias. Cada pedido tem uma forma de pagamento e um endereço de entrega.

Os relacionamentos das entidades da aplicação são:
- Entidades de **Produto** com uma relação de muitos para muitos com as entidades de **Categoria**.
- Entidades de **Cliente** com uma coleção de **Telefones** e uma relação de um para muitos com entidades de **Endereço**.
- Entidades de **Endereço** com uma relação de muitos para um com entidades de **Cidade**.
- Entidades de **Cidade** com uma relação de muitos para um com entidades de **Estado**.
- Entidades de **Pedido** com uma relação de muitos para um com entidades de **Cliente** e **Endereço**.

![Diagrama](https://github.com/eduardorcury/e-commerce-app-backend/blob/master/Diagrama.png)

### Operações CRUD

#### Categorias

HTTP GET e POST para endpoints */categorias*
```
https://curso-spring-eduardo.herokuapp.com/categorias
```
HTTP GET, PUT e DELETE para endpoints */categorias/{id}*
```
https://curso-spring-eduardo.herokuapp.com/categorias/1
```
Paginação da requisição com os parâmetros de *page, linesPerPage, orderBy e direction*. O exemplo abaixo retorna a segunda página da lista das categorias com 3 itens por página, ordenadas pelo nome descendente.
```
https://curso-spring-eduardo.herokuapp.com/categorias/page?page=1&linesPerPage=3&orderBy=nome&direction=DESC
```

*Nota: Os métodos POST, PUT e DELETE estão somente disponíveis para para usuário com permissão de **ADMIN**.*

#### Produtos

HTTP GET para endpoints */produtos/{id}*
```
https://curso-spring-eduardo.herokuapp.com/produtos/1
```
Paginação da requisição com os parâmetros de *nome, categorias, page, linesPerPage, orderBy e direction*. A URL abaixo retorna os produtos que contém *"or"* no nome pertencentes às categorias 1,2,3,4 e 5, na primeira página ordenada por nome crescente.
```
https://curso-spring-eduardo.herokuapp.com/produtos/?nome=or&categorias=1,2,3,4,5&page=0&linesPerPage=5&orderBy=nome&direction=ASC
```

#### Produtos
