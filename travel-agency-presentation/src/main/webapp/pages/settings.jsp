<%@ page contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="static/header.html" %>
  <title>Contact</title>
</head>
<body>
<div class="site-wrapper">
  <div class="site-wrapper-inner">
    <div class="cover-container">
      <%@include file="static/navbar.jsp" %>
      <h2>Settings</h2>
      <form class="form-group" action="settings" method="post">
        <input type="password" name="newPassword" class="form-control" placeholder="New password"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
      </form>
      <%@include file="static/credits.html" %>
    </div>
  </div>
</div>
<%@include file="static/footer.html" %>
</body>
</html>