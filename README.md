# Estudo-Spring
[![Dependency Status](https://david-dm.org/gsag/Estudo-Spring.svg)](https://david-dm.org/gsag/Estudo-Spring)
[![devDependency Status](https://david-dm.org/gsag/Estudo-Spring/dev-status.svg)](https://david-dm.org/gsag/Estudo-Spring#info=devDependencies)<br/><br/>
Projeto CRUD usando IntelliJ IDEA para estudos com Spring MVC.</br>
Este projeto utiliza a integração de um módulo front-end em Angular JS com o back-end com Spring MVC RESTful, utilizando JSON como canal de comunicação.

### Como executar o projeto?
> As seguintes dependências são necessárias:
- Java 8
- NodeJS 0.12 ou maior
- Maven 3
- Bower
- GruntJS

*(lembre-se, você deve estar dentro da pasta do projeto)*
```shell
$ cd /caminho-até-o-projeto/Estudo-Spring
```

Usando linux, basta apenas rodar o executável client.sh e server.sh no terminal em abas distintas: 
```shell
$ sh server.sh
```
```shell
$ sh client.sh
```
--
###Configurações:
- Porta do servidor front-end: (9000) - Para alterar, basta ir no arquivo **Gruntfile.js**.
- Porta do servidor back-end: (9090) - Para alterar, basta ir no arquivo **pom.xml**.
- Configurações gerais do servidor Spring estão no pacote **config**.
- Constantes, rotas e o módulo do Angular estão no arquivo **app.js**.

--
###Observações:
- Lembre-se de criar um banco de dados mysql para que o projeto possa rodar. Você deverá configurar suas credênciais de acesso na classe **JPAConfiguration** do pacote **config**.
- O projeto front-end está localizado na raiz do diretório **webapp** dentro da pasta **app**, seguindo o caminho: /src/main/webapp/

###Screenshots:<br>
<img src="https://raw.githubusercontent.com/gsag/Estudo-Spring/master/src/main/webapp/app/assets/src/images/screenshots/screenshot01.png" width="400">
