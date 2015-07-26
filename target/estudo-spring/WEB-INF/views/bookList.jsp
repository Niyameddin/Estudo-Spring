<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Guilherme
  Date: 14/07/2015
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listagem de livros</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <h3>Listagem de livros</h3>
    ${mensagem}
    <table class="table table-hover table-bordered table-striped">
        <tr>
            <td>ISBN</td>
            <td>Titulo</td>
            <td>Autor</td>
            <td class="col-md-1">Edição</td>
            <td class="col-md-1">Editar</td>
            <td class="col-md-1">Deletar</td>

        </tr>
        <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.isbn}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.edition}</td>
            <td>
                <a href="edit/${book.isbn}">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                </a>
            </td>
            <td>
                <a href="delete/${book.isbn}">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                </a>
            </td>
        </tr>
        </c:forEach>
</body>
</html>
