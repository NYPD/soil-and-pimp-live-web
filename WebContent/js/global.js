// Variabled
const subscribeButton = document.getElementById('subscribe');
const modalMedium = document.getElementById('global-medium-modal');
const modalSmall = document.getElementById('global-small-modal');


//Initialization
let activeTab = document.querySelector('body').getAttribute('data-active-tab');

if(activeTab !== null) document.querySelector('#' + activeTab).classList.add('active');


//Listeners
subscribeButton.addEventListener('click', function () {
  
  let xhr = new XMLHttpRequest();
  
  xhr.open('GET', '/subscribe', true);
  xhr.onload = function() {
    
    if (xhr.status === 200) {
      modalMedium.querySelector('.modal-dialog').innerHTML = xhr.response;
      $(modalMedium).modal('show');
    } else {
      console.log('There was a problem with the request.');
    }
    
  };
  
  xhr.onerror = function() {
    // There was a connection error of some sort
  };
  
  xhr.send();
  
});