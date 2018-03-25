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
subscribeButton.addEventListener('click', function () {
  
  var $getSubscribeModalContentPromise = $.get('/subscribe');
  
  $getSubscribeModalContentPromise.done(function(modalContent) {
    
    $modalMedium.find('.modal-dialog').html(modalContent);
    $modalMedium.modal('show');
    
  });
  
});



