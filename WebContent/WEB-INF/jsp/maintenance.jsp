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
        
        <div class="col-xs-12 text-right">
           <a type="button" class="btn btn-primary" id="add-event">Add Event</a>
        </div>
      </div>
      
      <div class="row">
        <div class="table-responsive">
          <table class="table table-hover table-striped" id="event-table">
            <thead>
              <tr>
                <th class="edit-column"></th>
                <th>Name</th>
                <th class="text-right">Schedules</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${allEvents}" var="event">
                <tr class="event-row" data-event-key="${event.eventKey}">
                  <td>
                    <i class="fa fa-pencil fa-lg edit-event text-primary" aria-hidden="true"></i>
                    <i class="fa fa-ban fa-lg delete-event text-danger" aria-hidden="true"></i>
                  </td>
                  <td>${event.name}</td>
                  <td class="text-right">${event.schedules.size()}</td>
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