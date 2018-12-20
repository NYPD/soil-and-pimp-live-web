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


NProgress.configure({showSpinner: false});

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
    $modalSmall.find('.modal-dialog').html(soil.getGenericErrorModal());
  
  $modalSmall.modal('show');
  
}).ajaxStart(function() {
  NProgress.start();
}).ajaxStop(function() {
  NProgress.done();
})

document.querySelector('#submit-bug-report').addEventListener('click', function() {
  soil.popupCenter('https://gitreports.com/issue/soil-bugs/soil-and-pimp-live-web', 'Submit Bug Report', 650, 900);
});