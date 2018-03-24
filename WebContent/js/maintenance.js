document.getElementById('event-table').addEventListener('click', function(event) {
  
  var target = event.target;
  if(!target.classList.contains('edit-event')) return;
  
  var eventKey = target.getAttribute('data-event-key');
  
  var $getEditEventModalContentPromise = $.get('/admin/get-edit-event-modal-content', {eventKey: eventKey});
  
  $getEditEventModalContentPromise.done(function(modalContent) {
    $largeMedium.find('.modal-dialog').html(modalContent);
    $largeMedium.modal('show');
  });
  
});