<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Delete Event</h4>
  </div>
  <div class="modal-body">
    <p>Are you sure you want to delete the following event:</p>
    
    <ul>
      <li>${event.name}</li>
    </ul>
    
  </div>
  <div class="modal-footer">
    <a type="button" class="btn btn-default" data-dismiss="modal">Nah</a>
    <a type="button" class="btn btn-danger" id="delete-event" data-event-key="${event.eventKey}">Delete</a>
  </div>
</div>

<script src="${context}/js/modal/delete-event.js?v=${projectVersion}"></script>