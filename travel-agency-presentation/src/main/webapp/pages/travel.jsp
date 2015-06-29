<%@ page contentType="text/html" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="static/header.html" %>
  <title>Travel</title>
</head>
<body>
<div class="site-wrapper">

  <div class="site-wrapper-inner">

    <div class="cover-container">

      <%@include file="static/navbar.jsp" %>
      <div class="col-lg-4 pull-left">
        <div class="animated fadeIn" align="left">
          <p><a href="travel" class="hvr-grow">Tours</a></p>
          <p><a href="#shoppings" class="hvr-grow">Shoppings</a></p>
        </div>
      </div>
      <div class="col-lg-8 pull-right">
        <c:if test="${not empty tours}">

          <ul>
            <c:forEach var="listValue" items="${tours}">
              <li>${listValue}</li>
            </c:forEach>
          </ul>

        </c:if>
      </div>

      <%@include file="static/credits.html" %>

    </div>

  </div>

</div>
<%@include file="static/footer.html" %>
</body>
</html>
