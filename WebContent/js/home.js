//Initialization
document.querySelectorAll('.schedule .date-title').forEach(function(element) {
  var timeStamp = Number.parseInt(element.getAttribute('data-date-time-stamp'));
  element.innerHTML = new Date(timeStamp).toLocaleDateString();
});

window.document.querySelectorAll('.event-container').forEach(function(row) {
  
  var thumbnails = row.querySelectorAll('.schedule .thumbnail');
  
  var tallestThumbnailSize = 0;

  for (var i = 0; i < thumbnails.length; i++) {
    var offsetHeight = thumbnails[i].offsetHeight;
    if(offsetHeight > tallestThumbnailSize) tallestThumbnailSize = offsetHeight;
  };

  for (var i = 0; i < thumbnails.length; i++) {
    thumbnails[i].setAttribute('style','min-height:' + tallestThumbnailSize + 'px');
  };
  
});

$('i[data-toggle="tooltip"]').tooltip()