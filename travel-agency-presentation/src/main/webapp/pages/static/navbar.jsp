<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="masthead clearfix">

  <div class="inner">

    <c:choose>
      <c:when test="${sessionScope.user == null}">
        <h2><a class="masthead-brand hvr-grow" href="/">Be Free</a></h2>
      </c:when>
      <c:otherwise>
        <h2><a class="masthead-brand hvr-grow" href="/settings">${sessionScope.user.email}</a></h2>
      </c:otherwise>
    </c:choose>


    <nav>

      <ul class="nav masthead-nav">

        <li class="${pageContext.request.requestURI.contains("travel") ? 'active' : 'hvr-grow'}">
          <a href="travel">Travel</a>
        </li>

        <li class="${pageContext.request.requestURI.contains("contact") ? 'active' : 'hvr-grow'}">
          <a href="contact">Contact</a>
        </li>

        <c:choose>
          <c:when test="${sessionScope.user == null}">
            <li class="${pageContext.request.requestURI.contains("login") ? 'active' : 'hvr-grow'}">
              <a href="login">Login</a>
            </li>
          </c:when>

          <c:otherwise>
            <li class="hvr-grow">
              <a href="logout">Logout</a>
            </li>
          </c:otherwise>

        </c:choose>
      </ul>

    </nav>

  </div>

</div>

