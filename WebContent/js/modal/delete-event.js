var deleteEventButton = document.getElementById('delete-event');

deleteEventButton.addEventListener('click', function(event) {
  
  var eventKey = event.target.getAttribute('data-event-key');
  
  var $deleteEventPromise = $.post('/admin/delete-event', {eventKey: eventKey});
  
  $deleteEventPromise.done(function() {
    let eventRow = document.querySelector('.event-row[data-event-key="' + eventKey +'"]');
    eventRow.parentElement.removeChild(eventRow);
    $modalSmall.modal('hide');
  });
  
});

//# sourceURL=delete-event.js