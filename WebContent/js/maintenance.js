document.getElementById('event-table').addEventListener('click', function(event) {
  
  let target = event.target;
  
  let isEditEvent = target.classList.contains('edit-event');
  let isDeleteEvent = target.classList.contains('delete-event');
  
  if(!isEditEvent && !isDeleteEvent) return;
  
  var eventKey = target.parentElement.getAttribute('data-event-key');
  
  if(isEditEvent)
    editEvent(eventKey);
  else
    deleteEvent(eventKey); 
});

function editEvent(eventKey) {
  
  var $getEditEventModalContentPromise = $.get('/admin/get-edit-event-modal-content', {eventKey: eventKey});
  
  $getEditEventModalContentPromise.done(function(modalContent) {
    $modalLarge.find('.modal-dialog').html(modalContent);
    $modalLarge.modal('show');
  });
  
}

function deleteEvent(eventKey) {
  
   var $getEditEventModalContentPromise = $.get('/admin/get-delete-event-modal-content', {eventKey: eventKey});
   
   $getEditEventModalContentPromise.done(function(modalContent) {
     $modalSmall.find('.modal-dialog').html(modalContent);
     $modalSmall.modal('show');
   });
   
 }