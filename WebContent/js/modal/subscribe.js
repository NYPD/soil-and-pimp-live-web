var submitEmailButton = document.getElementById('submit-email');
var subscribeCloseButton = document.getElementById('subscribe-close');
var successSubscribeMessage = document.querySelector('.success-subscribe-message');
var emailAddressInput = document.getElementById('email-address');

submitEmailButton.addEventListener('click', function () {
  
  let invalidEmail = !ritsu.validate(emailAddressInput);
  
  if(invalidEmail) {
    ritsu.showErrorMessages(emailAddressInput);
    return;
  }
  
  var $submitEmailPromise = $.post('/submit-email', {email: emailAddressInput.value});
  
  $submitEmailPromise.done(function() {
    
    subscribeCloseButton.innerHTML = 'Close';
    emailAddressInput.value = '';
    successSubscribeMessage.classList.add('show');
    
    setTimeout(() => {
      successSubscribeMessage.classList.remove('show');
    }, 5000);
    
  });
  
});

//# sourceURL=subscribe.js