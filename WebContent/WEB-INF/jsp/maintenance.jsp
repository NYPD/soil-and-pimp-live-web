<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <%@ include file="fragment/head-block.jsp" %>
    
    <title>SOIL &amp; "PIMP" LIVE - Admin</title>

    <link href="${context}/css/event-container.css" rel="stylesheet">
  </head>
  
  <body>
  
    <%@ include file="fragment/navigation.jsp" %>

    <div class="container">
    
      <div class="row">
        <div class="col-xs-12">
          <h1>All Events</h1>
        </div>
      </div>
        
      <c:forEach items="${pastEvents}" var="pastEvent">
      </c:forEach>
      
    </div>

    <%@ include file="fragment/footer.jsp" %>
    
    <!-- Scripts -->
    <script src="${context}/js/vendor/jquery-3.3.1.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="${context}/js/maintenance.js"></script>
  </body>

</html>