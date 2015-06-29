<%@ page contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="static/header.html" %>
  <title>Admin</title>
</head>

<body>
<div class="site-wrapper">
  <div class="site-wrapper-inner">
    <div class="cover-container">
      <%@include file="static/admin-navbar.jsp" %>
      <div class="inner cover">
        <h1 class="cover-heading">Admin mode.</h1>
        <p class="lead">You are in admin mode now.</p>
        <p class="lead">
          <a href="admin/users" class="btn btn-lg btn-default">View users</a>
        </p>
      </div>
      <%@include file="static/credits.html" %>
    </div>
  </div>
</div>
<%@include file="static/footer.html" %>
</body>
</html>
