var saveEventButton = document.getElementById('save-event');

saveEventButton.addEventListener('click', function() {
  
  let serialziedForm = $('.event-modal-content').find('input, textarea').serialize();
  let $saveEventFormPromise = $.post('/admin/save-event', serialziedForm);
  
  $saveEventFormPromise.done(function() {
  
    let isEditMode = document.getElementById('event-key') !== null;
    console.log(isEditMode);
    
  });
  
});