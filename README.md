<p align="center">
  <img width="378" height="97" src="https://github.com/eduardorcury/e-commerce-app-backend/blob/master/springlogo.svg">
</p>

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

HTTP GET e POST para endpoint */categorias*
```
https://curso-spring-eduardo.herokuapp.com/categorias
```
HTTP GET, PUT e DELETE para endpoint */categorias/{id}*
```
https://curso-spring-eduardo.herokuapp.com/categorias/1
```
Paginação da requisição com os parâmetros de *page, linesPerPage, orderBy e direction*. O exemplo abaixo retorna a segunda página da lista das categorias com 3 itens por página, ordenadas pelo nome descendente.
```
https://curso-spring-eduardo.herokuapp.com/categorias/page?page=1&linesPerPage=3&orderBy=nome&direction=DESC
```

*Nota: Os métodos POST, PUT e DELETE estão somente disponíveis para para usuário com permissão de **ADMIN**.*

#### Produtos

HTTP GET para endpoint */produtos/{id}*
```
https://curso-spring-eduardo.herokuapp.com/produtos/1
```
Paginação da requisição com os parâmetros de *nome, categorias, page, linesPerPage, orderBy e direction*. A URL abaixo retorna os produtos que contém *"or"* no nome pertencentes às categorias 1,2,3,4 e 5, na primeira página ordenada por nome crescente.
```
https://curso-spring-eduardo.herokuapp.com/produtos/?nome=or&categorias=1,2,3,4,5&page=0&linesPerPage=5&orderBy=nome&direction=ASC
```

#### Clientes

HTTP GET para endpoint */clientes* para usuário com permissão de *ADMIN* e */clientes/{id}* para o usuário logado. Retorna todos os clientes cadastrados no banco de dados ou os dados do cliente com o *id* especificado. Para fazer o login de um usuário, veja a seção de autenticação.

HTTP POST para endpoint */clientes*. Permite o cadastro de novos clientes.

HTTP PUT para endpoint */clientes/{id}* disponível para o usuário logado. Permite a modificação dos dados cadastrados.

#### Estados

HTTP GET para endpoint */estados*. Retorna os estados cadastrados.

```
https://curso-spring-eduardo.herokuapp.com/estados
```

HTTP GET para endpoint */estados/{estadoId}/cidades*. Retorna as cidades cadastradas do estado com *id* igual a *estadoId*.

```
https://curso-spring-eduardo.herokuapp.com/estados/2/cidades
```

#### Pedidos

HTTP GET e POST para endpoint */pedidos*. Retorna os pedidos do usuário logado e permite cadastro de novos pedidos. Para fazer o login de um usuário, veja a próxima seção.

### Autenticação e Autorização

É possível fazer o login de uma conta com método HTTP POST através do endpoint */login*, passando o email e senha cadastrados:

```json
{
	"email": "duducury10@gmail.com",
	"senha": "123"
}
```

A requisição retorna um header Authorization, com o **token JWT** que deve ser passado para as demais requisições.

Caso o cliente tenha esquecido da senha, é possível recadastrá-la a partir do endpoint */auth/forgot*. Será enviado um email com uma nova senha gerada aleatoriamente.

O token JWT tem duração de 1 dia, definida em [application.properties](https://github.com/eduardorcury/e-commerce-app-backend/blob/master/src/main/resources/application.properties). É possível atualizar o token no endpoint */auth/refresh_token*.

### Envio de Email

O serviço de Email permite que seja enviado um email com um resumo da compra após a finalização do pedido, além do envio de email quando houver uma requisição ao endpoint */auth/forgot*.

### Bucket Amazon S3

As imagens das categorias, produtos e dos clientes são armazenadas com o serviço Amazon S3.

## :mag_right: &nbsp;&nbsp; Como Usar

O backend já está hospedado como um [herokuapp](https://curso-spring-eduardo.herokuapp.com/). Por razões de segurança, o último commit desse projeto não é a versão de produção.

Caso se queira usar esse projeto em produção, deve-se modificar as credenciais da Amazon S3 no arquivo [application.properties](https://github.com/eduardorcury/e-commerce-app-backend/blob/master/src/main/resources/application.properties).

```
spring.profiles.active=prod

aws.access_key_id = [credenciais Amazon S3]
aws.secret_access_key = [credenciais Amazon S3]
s3.bucket = [Nome do bucket]
s3.region = [Região do bucket]
```
Deve-se também incluir as credenciais do email no arquivo [application-prod.properties](https://github.com/eduardorcury/e-commerce-app-backend/blob/master/src/main/resources/application-prod.properties).

```
spring.mail.username=[Credenciais do email]
spring.mail.password=[Credenciais do email]
```
