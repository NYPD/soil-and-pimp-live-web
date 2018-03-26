let unsubscribeButton = document.getElementById('unsubscribe');
let emailInput = document.getElementById('email');
let unsubscribeAlertSuccess = document.getElementById('success-unsubscribe');


unsubscribeButton.addEventListener('click', function() {
  
  let invalidEmail = !ritsu.validate(emailInput);
  if(invalidEmail) {
    ritsu.showErrorMessages(emailInput);
    return;
  }
  
  let $unsubscribePromise = $.post('unsubscribe-email-from-site', {email: emailInput.value});
  
  $unsubscribePromise.done(function() {
    
    showSuccessAlert();
    
  });
  
});


if(emailInput.value !== '') showSuccessAlert();


function showSuccessAlert() {
  
  unsubscribeAlertSuccess.classList.add('show');
  
  window.setTimeout(function() {
    
    unsubscribeAlertSuccess.classList.remove('show');
    
  }, 5000);
}
  
  
  