
<%--
  Created by IntelliJ IDEA.
  User: kietlam
  Date: 9/8/2017
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:body>
        <c:forEach items="${listDashboard}" var="item">
           <a href="/pentaho/${item.id}"> ${item.name}</a>
            <br>
        </c:forEach>

    </jsp:body>
</t:layout>