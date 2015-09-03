<%--suppress XmlDuplicatedId --%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Guilherme
  Date: 14/07/2015
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<customTags:pageTemplate title="Edição de Livro">
    <section class="container">

        <h3 style="padding-top: 70px;">Edição de Livro</h3>
        ${mensagem}
        <form:form action="${spring:mvcUrl('updateBook').build()}" commandName="bookDTO" cssClass="row col-md-4" method="post">

            <div class="form-group">
                <label for="isbn">ISBN</label>
                <form:input path="isbn" id="field_isbn" cssClass="form-control" maxlength="13" readonly="true"/>
            </div>

            <div class="form-group">
                <label for="title">Título</label>
                <form:input path="title" id="field_title" cssClass="form-control"/>
                <form:errors path="title" cssClass="bg-danger"/>
            </div>

            <div class="form-group">
                <label for="author">Autor</label>
                <form:input path="author" id="field_author" cssClass="form-control"/>
                <form:errors path="author" cssClass="bg-danger"/>
            </div>

            <div class="form-group">
                <label for="edition">Edição</label>
                <form:input path="edition" id="field_edition" cssClass="form-control" maxlength="2"/>
                <form:errors path="edition" cssClass="bg-danger"/>
            </div>

            <div class="form-group">
                <input class="btn btn-default" type="submit" value="Atualizar livro">
            </div>
        </form:form>
    </section>
</customTags:pageTemplate>
