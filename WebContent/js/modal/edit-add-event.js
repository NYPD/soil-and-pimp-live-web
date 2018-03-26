var saveEventButton = document.getElementById('save-event');
var addScheduleButton = document.getElementById('add-schedule');
var eventTable = document.getElementById('event-table');

saveEventButton.addEventListener('click', function() {
  
  let invalidForm = !ritsu.validate('.event-modal-content');
  if(invalidForm) {
    ritsu.showErrorMessages('.event-modal-content');
    return;
  }
  
  
  let serialziedForm = $('.event-modal-content').find('input, textarea').serialize();
  let $saveEventFormPromise = $.post('/admin/save-event', serialziedForm);
  
  $saveEventFormPromise.done(function(eventRowMarkup) {
  
    let isEditMode = document.getElementById('event-key') !== null;
    
    if(isEditMode) {
      let eventKey = document.getElementById('event-key').value;
      let eventRow = eventTable.querySelector('.event-row[data-event-key="' + eventKey +'"]');
      eventRow.insertAdjacentHTML('afterend', eventRowMarkup);
      eventRow.parentElement.removeChild(eventRow);
    }else {
      eventTable.querySelector('tbody tr:last-child').insertAdjacentHTML('afterend', eventRowMarkup);
    }
    
    $modalLarge.modal('hide');
    
  });
  
});


addScheduleButton.addEventListener('click', function() {
  
  let scheduleTable = document.getElementById('schedule-table');
  let scheduleColunt = scheduleTable.querySelectorAll('tbody tr').length;
  
  let $getAddScheduleRowContentPromise = $.post('/admin/get-add-schedule-row-content', {index: scheduleColunt});
  
  $getAddScheduleRowContentPromise.done(function(scheduleRowMarkup) {
    $(scheduleTable).append(scheduleRowMarkup)
  });
  
});

//# sourceURL=edit-add-event.js