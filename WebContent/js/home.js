//Initialization
document.querySelectorAll('.schedule .date-title').forEach(function(element) {
  
  var year = Number.parseInt(element.getAttribute('data-date-year'));
  var month = Number.parseInt(element.getAttribute('data-date-month'));
  var day = Number.parseInt(element.getAttribute('data-date-day'));
  
  element.innerHTML = new Date(year, month, day).toLocaleDateString();
});

window.document.querySelectorAll('.event-container').forEach(function(row) {
  
  let thumbnails = row.querySelectorAll('.schedule .thumbnail');
  
  let tallestThumbnailSize = 0;

  for (var i = 0; i < thumbnails.length; i++) {
    var offsetHeight = thumbnails[i].offsetHeight;
    if(offsetHeight > tallestThumbnailSize) tallestThumbnailSize = offsetHeight;
  };

  for (var i = 0; i < thumbnails.length; i++) {
    thumbnails[i].setAttribute('style','min-height:' + tallestThumbnailSize + 'px');
  };
  
});

$('i[data-toggle="tooltip"]').tooltip();