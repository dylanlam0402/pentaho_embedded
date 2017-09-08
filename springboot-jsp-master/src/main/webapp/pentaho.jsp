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
      <iframe src="http://localhost:8080/pentaho/api/repos/%3Ahome%3Aadmin%3Asingle_page.wcdf/generatedContent"></iframe>
    </jsp:body>
</t:layout>