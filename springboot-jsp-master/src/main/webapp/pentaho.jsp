<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<t:layout>

<jsp:body>

<div id="wrapper">

    <!-- Navigation -->


        <!-- Sidebar -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">

                <ul class="nav" id="side-menu">

                    <li>
                        <a href="#"><i class="fa fa-dashboard fa-fw"></i>Dashboard<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <c:forEach items="${listDashboard}" var="item">
                                <li>
                                    <a href="/pentaho/${item.id}"> ${item.getNameReport()}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>

            </div>
        </div>

    <!-- Page Content -->


</div>


</jsp:body>

</t:layout>
<!-- jQuery -->

