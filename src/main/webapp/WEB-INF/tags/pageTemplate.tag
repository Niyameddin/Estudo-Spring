<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@attribute name="title" required="true" %>
<!doctype html>
<html lang="pt">
<head>
    <title>${title}</title>
    <jsp:include page="/WEB-INF/views/template/head.jsp"/>
</head>
<body>
    <%@include file="/WEB-INF/views/template/nav.jsp" %>
    <jsp:doBody/>
    <%@include file="/WEB-INF/views/template/footer.jsp" %>
</body>
</html>