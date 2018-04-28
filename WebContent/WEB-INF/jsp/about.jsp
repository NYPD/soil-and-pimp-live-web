<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <%@ include file="fragment/head-block.jsp" %>
    
    <title>SOIL &amp; "PIMP" LIVE - About</title>
  </head>
  
  <body>
  
    <%@ include file="fragment/navigation.jsp" %>

    <div class="container">
    
      <div class="row">
        <div class="col-xs-12">
          <h1>About Us</h1>
          <p>
            This website has no  affiliation to SOIL &amp; "PIMP" SESSIONS. This is simply a fan-made 
            <a href="https://github.com/NYPD/soil-and-pimp-live-web" target="_blank">open source</a> website used to automatically collect and store 
            SOIL &amp; "PIMP" SESSIONS live events. This fan site was created for users who want to get notified when a new event gets posted or 
            simply wants to look at all upcoming and past events.
          </p> 
          <p>           
            Behind the scenes an <a href="https://github.com/NYPD/soil-and-pimp-live-batch" target="_blank">open source batch application</a> runs
            every 30 minutes to check and see if any new events got posted on the SOIL &amp; "PIMP" SESSIONS website and if so gets pulled and stored
            on our database. The website simply retrieves and shows all the events that got stored on our database.
          </p>
          <p>
            If you see a missing event, encounter a bug while using the site, or simply have suggestions or feedback feel free to submit an issue
            on our <a href="https://github.com/NYPD/soil-and-pimp-live-web/issues">GitHub page.</a>
          </p>
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