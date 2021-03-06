<link href="${context}/css/modal/subscribe.css" rel="stylesheet">

<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Subscribe</h4>
  </div>
  <div class="modal-body">
    
    <div class="row">
      <div class="col-xs-12">
        <p>
          Receive newly posted SOIL &amp; "PIMP" SESSIONS events! Make sure to check the spam folder for our emails in case they incorrectly get 
          flagged for spam or simply add <em>events@soilandpimp.live</em> to your whitelist.
        </p>
      </div>
    </div>
    
    <form class="form-horizontal">
      <div class="form-group">
        <label for="email-address" class="col-sm-3 control-label">Email Address:</label>
        <div class="col-sm-9">
          <input type="text" class="form-control alpha alpha-email" id="email-address" required placeholder="josie@pimp.com">
        </div>
      </div>
    </form>
    
    <div class="row">
      <div class="col-xs-12">
        <small>For information on how your email address is used please see our <a href="/privacy-policy">privacy policy.</a></small>
      </div>
    </div>
    
  </div>
  <div class="modal-footer">
  
    <p class="text-success success-subscribe-message">Subscribed! Please verify your email address</p>
  
    <a type="button" class="btn btn-default" id="subscribe-close" data-dismiss="modal">I'll pass</a>
    <a type="button" class="btn btn-primary" id="submit-email">Subscribe</a>
  </div>
</div>

<script src="${context}/js/modal/subscribe.js?v=${projectVersion}"></script>