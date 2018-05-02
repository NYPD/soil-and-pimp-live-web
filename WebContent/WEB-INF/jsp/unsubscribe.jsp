<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <%@ include file="fragment/head-block.jsp" %>
    
    <link href="${context}/css/unsubscribe.css?v=${projectVersion}" rel="stylesheet">
    
    <title>SOIL &amp; "PIMP" LIVE - Unsubscribe</title>
  </head>
  
  <body>
  
    <%@ include file="fragment/navigation.jsp" %>

    <div class="container">
    
      <div class="row">
      
        <div class="col-xs-12">
          <h1>Unsubscribe</h1>
        </div>
      
      </div>
      <div class="row">
        
        <div class="col-xs-6">
          <p>
            Don't want to receive event notification emails anymore? Well, we don't want you either. Submit your email on the right and we 
            will gladly delete it from our servers so you can get out of here.
          </p>
        </div> 
        
        <div class="col-xs-6">
        
          <form class="form-inline">
            <div class="form-group">
              <label for="email">Email Address</label>
              <input type="email" class="form-control alpha alpha-email" id="email" placeholder="midorin@email.com" value ="${emailAddress}" required>
            </div>
            <a type="button" class="btn btn-primary" id="unsubscribe">Unsubscribe</a>
          </form>
          
          <div class="alert alert-success" role="alert" id="success-unsubscribe">Successfully unsubscribed</div>
          
        </div>
      </div>
      
    </div>

    <%@ include file="fragment/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file="fragment/global-modals.jsp" %>
    
    <!-- Scripts -->
    <%@ include file="fragment/global-javascript.jsp" %>
    <script src="${context}/js/unsubscribe.js?v=${projectVersion}"></script>
  </body>

</html>