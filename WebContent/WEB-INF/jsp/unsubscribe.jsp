<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <%@ include file="fragment/head-block.jsp" %>
    
    <title>SOIL &amp; "PIMP" LIVE - Unsubscribe</title>
  </head>
  
  <body>
  
    <%@ include file="fragment/navigation.jsp" %>

    <div class="container">
    
      <div class="row">
        <div class="col-xs-12">
        
          <h1>Unsubscribe</h1>
          
          <p>
            Don't want to receive event notification emails anymore? Well, we don't want you either. Submit your email below and we will delete from
            our servers so you can get out of here.
          </p>
          
          <form class="form-inline">
            <div class="form-group">
              <label for="email">Email Address</label>
              <input type="email" class="form-control" id="email" placeholder="midorin@email.com">
            </div>
            <button type="submit" class="btn btn-primary">Unsubscribe</button>
          </form>

        </div>
      </div>
        
    </div>

    <%@ include file="fragment/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file="fragment/global-modals.jsp" %>
    
    <!-- Scripts -->
    <%@ include file="fragment/global-javascript.jsp" %>
  </body>

</html>