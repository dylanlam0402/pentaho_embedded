<%--
  Created by IntelliJ IDEA.
  User: kietlam
  Date: 9/8/2017
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/js/ie10-viewport-bug-workaround.js"></script>
    <link rel="stylesheet" href="/css/sticky-footer-navbar.css">
    <link rel="stylesheet" href="/css/ie10-viewport-bug-workaround.css">
</head>
<body>
<div id="pageheader">

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img src="/images/lexisnexis.png" alt="Lexis Nexis" style="width:200px;height:50px;">
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                    <li><a href="login.jsp">Pentaho</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
    <jsp:invoke fragment="header"/>
</div >
<div id="body">
    <div class="body-container">
        <jsp:doBody/>
    </div>


</div>
<div id="pagefooter">
    <footer class="footer">
        <div class="container">
            <p class="text-muted"> &copy; Lexis nexis </p>
        </div>
    </footer>
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>
