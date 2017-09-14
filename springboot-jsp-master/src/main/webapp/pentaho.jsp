
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
        <script>
            var authen = "<c:out value = "${authentication}"/>"
            $(document).ready(function () {
                if(authen!="")
                {
                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8080/pentaho/api/repo/files/%3A/tree?depth",
                        headers :{"Authorization":authen}
                    });
                    $("#output_iframe_id").attr('src',"http://localhost:8080/pentaho/api/repos/%3Ahome%3Aadmin%3AChild.wcdf/generatedContent");
                }
            })
        </script>
        <iframe id="output_iframe_id" src=""></iframe>
    </jsp:body>
</t:layout>