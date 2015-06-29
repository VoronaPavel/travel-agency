<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="masthead clearfix">
  <div class="inner">
    <h2><a class="masthead-brand" href="admin">Admin</a></h2>
    <nav>
      <ul class="nav masthead-nav">
        <li class="${pageContext.request.requestURI.contains("travel") ? 'active' : 'hvr-grow'}">
          <a href="travel">Travel</a>
        </li>
        <li class="${pageContext.request.requestURI.contains("contact") ? 'active' : 'hvr-grow'}">
          <a href="contact">Contact</a>
        </li>
        <li class="hvr-grow">
          <a href="logout">Logout</a>
        </li>
      </ul>
    </nav>
  </div>
</div>