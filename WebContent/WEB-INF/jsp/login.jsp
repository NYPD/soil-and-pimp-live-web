<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en-US">

  <head>
  
    <title>Soil Login</title>
  
    <%@ include file = "fragment/meta-block.jsp" %>

    <link href="${context}/css/login.css?v=${projectVersion}" rel="stylesheet">
    
  </head>
  
  <body>
  
    <div class="container">
      
      <div class="login-container">
      
        <h2>Please Sign In</h2>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" class="stay-signed-in"> Stay signed in
          </label>
        </div>
        
        <div class="vertical-rule"></div>
        
        <div class="login-options">
          <img class="login-image" alt="Google Login" src="${context}/images/btn_google_signin_dark_normal_web.png" data-href="admin/api/google-oauth-login" data-prev-path="${prevPath}">
        </div>
        
      </div>
      
      <c:if test="${not empty loginErrorMessage}">
        <div class="alert alert-warning alert-dismissible col-xs-12 col-sm-offset-2 col-sm-8" role="alert">
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <span>${loginErrorMessage}</span>
        </div>
      </c:if>
      
    </div>
    
    <!-- Scripts -->
    <script src="${context}/js/vendor/jquery-3.3.1.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="${context}/js/login.js?v=${projectVersion}"></script>
    
  </body>
  
</html>