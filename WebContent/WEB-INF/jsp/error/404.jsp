<!DOCTYPE html>
<html lang="en-US">

  <head>
  
    <%@ include file = "../fragment/head-block.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>404</title>

    <!-- Styles -->
    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/vendor/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Baloo" rel="stylesheet">
    <link href="${context}/css/global.css" rel="stylesheet">
    <link href="${context}/css/404.css" rel="stylesheet">
    
    <script type="text/javascript">
      sessionStorage.setItem('context', '${context}');
    </script>

  </head>
  
  <body>
  
    <%@ include file = "../fragment/navigation.jsp" %>
    
    
    <img alt="shacho-404" src="${context}/images/404-shacho.png" class="center-block four-o-four-image">
    
    <div class="container">
      
      <div class="row">
     
        <div class="col-md-12 col-text-container">
          <h1>404</h1>
          <p>No death jazz here<br> <small>(If it was our fault, sorry, please submit a bug report)</small></p>
        </div>
        
      </div>
      
    </div>
    
    <%@ include file = "../fragment/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file = "../fragment/global-modals.jsp" %>

    <!-- Scripts -->
    <script src="${context}/js/vendor/jquery-3.3.1.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    
  </body>
  
</html>