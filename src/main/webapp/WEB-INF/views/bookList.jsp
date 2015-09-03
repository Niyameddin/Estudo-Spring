<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Guilherme
  Date: 14/07/2015
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<customTags:pageTemplate title="Listagem de Livro">
    <section class="container">
        <h3 style="padding-top: 70px;">Listagem de livros</h3>

        <div class="${cssStyle}" role="alert">${mensagem}</div>

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
                        <a class="btn btn-default" href="edit/${book.isbn}">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </a>
                    </td>
                    <td>
                        <form action="delete/${book.isbn}" method="post"
                              onsubmit="return confirm('Deseja realmente excluir este livro?');">
                            <a href="#">
                                <button class="btn btn-default" type="submit">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                            </a>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>
</customTags:pageTemplate>
