# Estudo-Spring
Projeto CRUD usando IntelliJ IDEA para estudos com Spring MVC e Angular JS.</br>
Integrando módulo front-end em Angular JS com back-end Spring MVC RESTful, utilizando JSON como canal de comunicação.

### Como executar o projeto?
> Você deve ter o node, o npm e o grunt instalados em seu repositório local.

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

> Lembre-se de criar um banco de dados mysql para que o projeto possa rodar. Você deverá configurar suas credênciais de acesso na classe JPAConfiguration do pacote config.

