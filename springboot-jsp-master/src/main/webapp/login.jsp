<%--
  Created by IntelliJ IDEA.
  User: congle
  Date: 9/7/2017
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<link href="/css/login_page.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Dosis:300|Lato:300,400,600,700|Roboto+Condensed:300,700|Open+Sans+Condensed:300,600|Open+Sans:400,300,600,700|Maven+Pro:400,700" rel="stylesheet" type="text/css">
<link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" type="text/css">

<div class="content">
<div class="title">Lexis nexis</div>
    <form class="form-signin" action="/login" method="post">
<input type="text" placeholder="E-mail" name="userName"/>
<input type="password" placeholder="Password" name="password"/>
<input type="checkbox" id="rememberMe"/>
<label for="rememberMe"></label><span>Remember me</span>
<button type="submit">Login</button>
    </form>
</div>
