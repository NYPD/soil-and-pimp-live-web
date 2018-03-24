<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <%@ include file="fragment/head-block.jsp" %>
    
    <title>SOIL &amp; "PIMP" LIVE - Admin</title>

    <link href="${context}/css/maintenance.css" rel="stylesheet">
  </head>
  
  <body data-active-tab="maintenance">
  
    <%@ include file="fragment/navigation.jsp" %>

    <div class="container">
    
      <div class="row">
        <div class="col-xs-12">
          <h1>All Events</h1>
        </div>
      </div>
      
      <div class="row">
        <div class="table-responsive">
          <table class="table table-hover table-striped" id="event-table">
            <thead>
              <tr>
                <th></th>
                <th>Name</th>
                <th>Schedules</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${allEvents}" var="event">
                <tr>
                  <td>
                    <a class="cursor-pointer edit-event" data-event-key="${event.eventKey}">Edit</a>
                  </td>
                  <td>${event.name}</td>
                  <td>${event.schedules.size()}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        
      </div>
      
    </div>

    <%@ include file="fragment/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file="fragment/global-modals.jsp" %>
    
    <!-- Scripts -->
    <%@ include file="fragment/global-javascript.jsp" %>
    <script src="${context}/js/maintenance.js?v=${projectVersion}"></script>
  </body>

</html>