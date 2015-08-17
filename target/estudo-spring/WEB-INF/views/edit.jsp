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
    <title>Edição de Livro</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
</head>
<body class="container">
    <%--${spring:mvcUrl('saveBook').build()}--%>
    <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"><a href="/bookcase/books">Voltar</a></span>

    <h3>Edição de Livro</h3>
    ${mensagem}
    <form:form action="${spring:mvcUrl('updateBook').build()}" commandName="bookDTO" cssClass="row col-md-4" method="post">

        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input class="form-control" id="isbn" type="text" name="isbn" maxlength="13" value="${book.isbn}" readonly/>
        </div>

        <div class="form-group">
            <label for="title">Título</label>
            <input class="form-control" id="title" type="text" name="title" value="${book.title}"/>
            <form:errors path="title" cssClass="bg-danger"/>
        </div>

        <div class="form-group">
            <label for="author">Autor</label>
            <input class="form-control" id="author" type="text" name="author" value="${book.author}"/>
            <form:errors path="author" cssClass="bg-danger"/>
        </div>

        <div class="form-group">
            <label for="edition">Edição</label>
            <input class="form-control" id="edition" type="text" name="edition" maxlength="2" value="${book.edition}"/>
            <form:errors path="edition" cssClass="bg-danger"/>
        </div>

        <div class="form-group">
            <input class="btn btn-default" type="submit" value="Atualizar livro">
        </div>
    </form:form>
</body>
</html>
