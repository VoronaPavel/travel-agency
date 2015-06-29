<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="static/header.html" %>
  <title>Registration</title>
</head>
<body>
<div class="site-wrapper">
  <div class="site-wrapper-inner">
    <div class="cover-container">

      <%@include file="static/navbar.jsp" %>

      <form class="form-signin" action="registration" method="post">

        <h2 class="form-signin-heading animated pulse infinite">Sign up</h2>

        <div class="form-group">
          <input type="email" name="email" class="form-control" placeholder="Email address" required autofocus pattern="^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$">
        </div>

        <div class="form-group">
          <input type="password" name="password" class="form-control" placeholder="Password" required>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>

      </form>

      <a href="login">Login</a>

      <%@include file="static/credits.html" %>

    </div>
  </div>
</div>
</body>
</html>

