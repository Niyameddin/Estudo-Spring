<%--suppress ALL --%>
<%--suppress HtmlUnknownTarget --%>
<%--suppress XmlDuplicatedId --%>
<%--
  Created by IntelliJ IDEA.
  User: Guilherme
  Date: 14/07/2015
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=cp1252" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Cadastrar um livro</title>
    <jsp:include page="template/head.jsp" />
</head>
<body>
    <jsp:include page="template/nav.jsp" />

    <section class="container">
        <h3 style="padding-top: 70px;">Cadastro de Livro</h3>
        ${mensagem}

        <form:form action="${spring:mvcUrl('saveBook').build()}" commandName="bookDTO" cssClass="row col-md-4" method="post">

            <div class="form-group">
                <label for="isbn">ISBN</label>
                <form:input path="isbn" id="field_isbn" cssClass="form-control" maxlength="13"/>
                <form:errors path="isbn" cssClass="bg-danger"/>
            </div>

            <div class="form-group">
                <label for="title">T�tulo</label>
                <form:input path="title" id="field_title" cssClass="form-control"/>
                <form:errors path="title" cssClass="bg-danger"/>
            </div>

            <div class="form-group">
                <label for="author">Autor</label>
                <form:input path="author" id="field_author" cssClass="form-control"/>
                <form:errors path="author" cssClass="bg-danger"/>
            </div>

            <div class="form-group">
                <label for="edition">Edi��o</label>
                <form:input path="edition" id="field_edition" cssClass="form-control" maxlength="2"/>
                <form:errors path="edition" cssClass="bg-danger"/>
            </div>

            <div class="form-group">
                <input class="btn btn-default" type="submit" value="Cadastrar livro">
            </div>
        </form:form>
    </section>
</body>
</html>
