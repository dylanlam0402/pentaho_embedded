<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>

        <h1>Home page </h1>
        <h2>  <c:choose>
            <c:when test="${username.equals('')}">
            </c:when>
            <c:otherwise>
                hello ${username}
            </c:otherwise>
        </c:choose></h2>

    </jsp:body>
</t:layout>