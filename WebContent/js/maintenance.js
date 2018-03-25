let addEventButton = document.getElementById('add-event');

addEventButton.addEventListener('click', function(event) {
  editAddEvent();
});


document.getElementById('event-table').addEventListener('click', function(event) {
  
  let target = event.target;
  
  let isEditEvent = target.classList.contains('edit-event');
  let isDeleteEvent = target.classList.contains('delete-event');
  
  if(!isEditEvent && !isDeleteEvent) return;
  
  var eventKey = target.parentElement.parentElement.getAttribute('data-event-key');
  
  if(isEditEvent)
    editAddEvent(eventKey);
  else
    deleteEvent(eventKey); 
});

function editAddEvent(eventKey) {
  
  var eventKeyIsUndefined = eventKey === undefined;
  
  var $getEditEventModalContentPromise = eventKeyIsUndefined? $.get('/admin/get-edit-add-event-modal-content') : 
                                                              $.get('/admin/get-edit-add-event-modal-content', {eventKey: eventKey});
  
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