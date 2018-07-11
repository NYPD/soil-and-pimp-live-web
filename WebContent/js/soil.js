const soil = (function() {
  
  var popupCenter = function(url, title, w, h) {
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) newWindow.focus();
    
  }

  var getGenericErrorModal = function() {
    
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
  
  return {
    popupCenter: popupCenter,
    getGenericErrorModal: getGenericErrorModal
  }
  
})();