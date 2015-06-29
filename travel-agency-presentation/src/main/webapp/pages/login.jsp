<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="WEB-INF/tags.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="static/header.html" %>
  <title>Login</title>
</head>
<body>
<div class="site-wrapper">
  <div class="site-wrapper-inner">
    <div class="cover-container">
      <%@include file="static/navbar.jsp" %>
      <p:error attribute="${'ERROR'}"/>
      <form class="form-signin" action="login" method="post">
        <h2 class="form-signin-heading animated pulse infinite">Please sign in</h2>
        <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
      <a href="registration">Register</a>
      <%@include file="static/credits.html" %>
    </div>
  </div>
</div>
</body>
</html>

