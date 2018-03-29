<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${context}/css/modal/edit-add-event.css" rel="stylesheet">

<c:choose>
  <c:when test="${empty event}">
    <c:set var="modalTitle" value="Add Event"/>
  </c:when>
  <c:otherwise>
    <c:set var="modalTitle" value="Edit Event"/>
  </c:otherwise>
</c:choose>

<div class="modal-content event-modal-content">

  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">${modalTitle}</h4>
  </div>
  
  <div class="modal-body">
  
    <div class="row">
      <div class="col-xs-12">
        <h3>Event</h3>
      </div>
    </div>

    <form class="form-horizontal">
      
      <c:if test="${not empty event}">
        <input type="hidden" name="eventKey" id="event-key" value="${event.eventKey}">
      </c:if>
      
      <div class="form-group">
        <label for="event-name" class="col-sm-3 control-label">Name:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="event-name" name="name" value="${event.name}" required>
        </div>
      </div>
      <div class="form-group">
        <label for="social-networking-name" class="col-sm-3 control-label">Social Networking Title:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="social-networking-name" name="socialNetworkingTitle" value="${event.socialNetworkingTitle}">
        </div>
      </div>
      <div class="form-group">
        <label for="event-memo" class="col-sm-3 control-label">Memo:</label>
        <div class="col-sm-9">
          <textarea rows="4" class="form-control" id="event-memo" name="memo">${event.memo}</textarea>
        </div>
      </div>
      <div class="form-group">
        <label for="event-url" class="col-sm-3 control-label">Event URL:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="event-url" name="eventUrl" value="${event.eventUrl}">
        </div>
      </div>
      <div class="form-group">
        <label for="jvc-url" class="col-sm-3 control-label">JVC URL:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="jvc-url" name="jvcUrl" value="${event.jvcUrl}">
        </div>
      </div>
      <div class="form-group">
        <label for="open-date" class="col-sm-3 control-label">Open Date:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="open-date" name="openDate" value="${event.openDate}" placeholder="yyyy-mmm-ddThh:mm:ss">
        </div>
      </div>
    </form>
    
    <div class="row">
      <div class="col-xs-12">
        <h3>Schedules</h3>
      </div>
        
      <div class="col-xs-12 text-right">
        <a type="button" class="btn btn-primary" id="add-schedule">Add Schedule</a>
      </div>
      
      <div class="col-xs-12">
        
        <div class="table-responsive">
          <table class="table table-hover table-striped" id="schedule-table">
            <thead>
              <tr>
                <th></th>
                <th>Date</th>
                <th>Enter Time</th>
                <th>Start Time</th>
                <th>Prefecture</th>
                <th>Place</th>
                <th>Call</th>
                <th>Memo</th>
                <th>Link</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${event.schedules}" var="schedule" varStatus="loopTagStatus">
                <c:set var="index" value="${loopTagStatus.index}"/>
                <%@ include file="../fragment/schedule-modal-row.jsp" %>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="modal-footer">
    <a type="button" class="btn btn-default" data-dismiss="modal">Cancel</a>
    <a type="button" class="btn btn-primary" id="save-event">Save</a>
  </div>
</div>

<script src="${context}/js/modal/edit-add-event.js?v=${projectVersion}"></script>