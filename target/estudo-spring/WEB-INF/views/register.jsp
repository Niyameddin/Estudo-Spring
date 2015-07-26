<%--
  Created by IntelliJ IDEA.
  User: Guilherme
  Date: 14/07/2015
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Cadastrar um livro</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body class="container">
    <%--${spring:mvcUrl('saveBook').build()}--%>
    <h3>Cadastro de Livro</h3>
    ${mensagem}
    <form class="row col-md-4" action="save" method="post">

        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input class="form-control" id="isbn" type="text" name="isbn" maxlength="13"/>
        </div>

        <div class="form-group">
            <label for="title">Título</label>
            <input class="form-control" id="title" type="text" name="title"/>
        </div>

        <div class="form-group">
            <label for="author">Autor</label>
            <input class="form-control" id="author" type="text" name="author"/>
        </div>

        <div class="form-group">
            <label for="edition">Edição</label>
            <input class="form-control" id="edition" type="text" name="edition" maxlength="2"/>
        </div>

        <div class="form-group">
            <input class="btn btn-default" type="submit" value="Cadastrar">
        </div>
    </form>
</body>
</html>
