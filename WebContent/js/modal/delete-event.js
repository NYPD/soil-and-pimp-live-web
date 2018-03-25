var deleteEventButton = document.getElementById('delete-event');

deleteEventButton.addEventListener('click', function(event) {
  
  var eventKey = event.target.getAttribute('data-event-key');
  
  var $deleteEventPromise = $.post('/admin/delete-event', {eventKey: eventKey});
  
  $deleteEventPromise.done(function() {
    $modalSmall.modal('hide');
  });
  
});