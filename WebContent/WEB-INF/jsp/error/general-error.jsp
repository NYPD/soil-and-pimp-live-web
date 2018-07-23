<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <%@ include file="../fragment/head-block.jsp" %>
    <link href="${context}/css/general-error.css?v=${projectVersion}" rel="stylesheet">
    <title>SOIL &amp; "PIMP" LIVE - Error</title>
  </head>
  
  <body>
  
    <%@ include file="../fragment/navigation.jsp" %>

    <div class="container">
    
      <div class="row">
        <div class="col-xs-12">
        
          <p class="error-text-big">Well this is embarrassing...</p>
          <p class="error-text-small">
            Perhaps try again? Or maybe click on the "Submit Bug Report" link at the bottom if whatever is happening keeps happening.
          </p>
        
          <div class="image-container">
            <img id="soil-mound" alt="soil mound" src="${context}/images/soil-mound.png"> <br>
            <img class="hidden" id="pimp" alt="san andreas pimp" src="${context}/images/san-andreas-pimp.png">
          </div>
        
        </div>
      </div>
      
    </div>

    <%@ include file="../fragment/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file="../fragment/global-modals.jsp" %>
    
    <!-- Scripts -->
    <%@ include file="../fragment/global-javascript.jsp" %>
    
  </body>

</html>