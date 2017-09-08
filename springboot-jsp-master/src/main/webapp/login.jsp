<%--
  Created by IntelliJ IDEA.
  User: congle
  Date: 9/7/2017
  Time: 3:12 PM
  To change this template use File | Settings | File Templates.
--%>
<link href="/css/app.css" rel="stylesheet" type="text/css">
<script src="/js/app.js"></script>
<div class="login-page">
    <div class="form" name="${user}">
        <form class="register-form">
            <input type="text" placeholder="name" name="${user.username}"/>
            <input type="password" placeholder="password"/>
            <input type="text" placeholder="email address"/>
            <button>create</button>
            <p class="message">Already registered? <a href="#">Sign In</a></p>
        </form>
        <form class="login-form" action="/login" method="post">
            <input type="text" placeholder="username" name="userName"/>
            <input type="password" placeholder="password" name="password"/>
            <button>login</button>
            <p class="message">Not registered? <a href="#">Create an account</a></p>
        </form>
    </div>
</div>