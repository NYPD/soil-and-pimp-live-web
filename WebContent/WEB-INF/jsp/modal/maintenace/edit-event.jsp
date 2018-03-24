<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="${context}/css/modal/edit-event.css" rel="stylesheet">

<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Subscribe</h4>
  </div>
  <div class="modal-body">
  
    <div class="row">
      <div class="col-xs-12">
        <h3>Event</h3>
      </div>
    </div>
    
    <form class="form-horizontal">
      <div class="form-group">
        <label for="event-name" class="col-sm-3 control-label">Name:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control alpha alpha-email" id="event-name" value="${event.name}">
        </div>
      </div>
      <div class="form-group">
        <label for="social-networking-name" class="col-sm-3 control-label">Social Networking Title:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control alpha alpha-email" id="social-networking-name" value="${event.socialNetworkingTitle}">
        </div>
      </div>
      <div class="form-group">
        <label for="memo" class="col-sm-3 control-label">Memo:</label>
        <div class="col-sm-9">
          <textarea rows="4" class="form-control" id="memo">${event.memo}</textarea>
        </div>
      </div>
      <div class="form-group">
        <label for="event-url" class="col-sm-3 control-label">Event URL:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control alpha alpha-email" id="event-url" value="${event.eventUrl}">
        </div>
      </div>
      <div class="form-group">
        <label for="jvc-url" class="col-sm-3 control-label">JVC URL:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control alpha alpha-email" id="jvc-url" value="${event.jvcUrl}">
        </div>
      </div>
      <div class="form-group">
        <label for="open-date" class="col-sm-3 control-label">Open Date:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control alpha alpha-email" id="open-date" value="${event.openDate}">
        </div>
      </div>
    </form>
    
    <div class="row">
      <div class="col-xs-12">
        <h3>Schedules</h3>
        
        <div class="table-responsive">
          <table class="table table-hover table-striped" id="event-table">
            <thead>
              <tr>
                <th>Date</th>
                <th>Enter Time</th>
                <th>Start Time</th>
                <th>Prefecture</th>
                <th>Place</th>
                <th>Memo</th>
                <th>Link</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${event.schedules}" var="schedule">
                <tr>
                  <td>
                    <input type="text" class="form-control form-small alpha alpha-email" value="${schedule.date}">
                  </td>
                  <td>
                    <input type="text" class="form-control form-small alpha alpha-email" value="${schedule.enterTime}">
                  </td>
                  <td>
                    <input type="text" class="form-control form-small alpha alpha-email" value="${schedule.startTime}">
                  </td>
                  <td>
                    <input type="text" class="form-control form-small alpha alpha-email" value="${schedule.prefecture}">
                  </td>
                  <td>
                    <input type="text" class="form-control form-big alpha alpha-email" value="${schedule.place}">
                  </td>
                  <td>
                    <textarea rows="2" class="form-control form-big alpha alpha-email">${schedule.memo}</textarea>
                  </td>
                  <td>
                    <input type="text" class="form-control form-big alpha alpha-email"value="${schedule.link}">
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="modal-footer">
  
    <p class="text-success success-subscribe-message">Successfully subscribed!</p>
  
    <a type="button" class="btn btn-default" data-dismiss="modal">Cancel</a>
    <a type="button" class="btn btn-primary" id="save-event">Save</a>
  </div>
</div>

<script src="${context}/js/modal/subscribe.js?v=${projectVersion}"></script>