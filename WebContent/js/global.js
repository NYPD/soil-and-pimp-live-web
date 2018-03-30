// Variables
const subscribeButton = document.getElementById('subscribe');
const $modalLarge = $('#global-large-modal');
const $modalMedium = $('#global-medium-modal');
const $modalSmall = $('#global-small-modal');
const $errorModal = $('#error-modal');

const $allModals = $('.modal');


//Initialization
let activeTab = document.querySelector('body').getAttribute('data-active-tab');

if(activeTab !== null) document.querySelector('#' + activeTab).classList.add('active');

$(document).ajaxError(function() {
  $allModals.modal('hide');
  $errorModal.modal('show');
});

ritsu.initialize({
  useBootstrap3Stlying: true
});

//Listeners

if(subscribeButton !== null) {
  
  subscribeButton.addEventListener('click', function () {
    
    var $getSubscribeModalContentPromise = $.get('/subscribe');
    
    $getSubscribeModalContentPromise.done(function(modalContent) {
      
      $modalMedium.find('.modal-dialog').html(modalContent);
      $modalMedium.modal('show');
      
    });
    
  });
};

$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
  
  $allModals.modal('hide');
  
  var isServerError = jqXHR.status >= 500 && jqXHR.status < 600;
  var isUnauthorized = jqXHR.status === 401;
  
  if(isUnauthorized) {
    window.location.href = window.location.origin + '/admin?prevPath=' + window.location.pathname; 
    return;
  }
  
  if(isServerError)
    $modalSmall.find('.modal-dialog').html(jqXHR.responseText);
  else
    $modalSmall.find('.modal-dialog').html(getGenericErrorModal());
  
  $modalSmall.modal('show');
  
});


function getGenericErrorModal() {
  
  return '<div class="modal-content">' +

            '<div class="modal-header">' +
              '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
              '<h4 class="modal-title">Error</h4>' +
            '</div>' +
            
            '<div class="modal-body">' +
             '<p>Something is goofed up. If this keeps happening please submit a bug report.</p>' +
            '</div>' +
            
            '<div class="modal-footer">' +
              '<button type="button" class="btn btn-warning" data-dismiss="modal">OK</button>' +
            '</div>' +
            
          '</div>';
}