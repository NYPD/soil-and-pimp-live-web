<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<tr class="event-row" data-event-key="${event.eventKey}">
  <td>
    <i class="fa fa-pencil fa-lg edit-event text-primary" aria-hidden="true"></i>
    <i class="fa fa-ban fa-lg delete-event text-danger" aria-hidden="true"></i>
  </td>
  <td>${event.name}</td>
  <td class="text-right">${event.schedules.size()}</td>
</tr>