<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Stay up to date with the latest Soil & &ldquo;Pimp&rdquo; Sessions concerts around the world."> 
    
    <title>SOIL &amp; "PIMP" LIVE</title>

    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/vendor/font-awesome.min.css" rel="stylesheet">
    <link href="${context}/css/global.css" rel="stylesheet">
    <link href="${context}/css/home.css" rel="stylesheet">
  </head>
  
  <body>
  
    <%@ include file="fragment/navigation.jsp" %>

    <div id="main-carousel" class="carousel slide" data-ride="carousel">
  
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#main-carousel" data-slide-to="0" class="active"></li>
        <li data-target="#main-carousel" data-slide-to="1"></li>
        <li data-target="#main-carousel" data-slide-to="2"></li>
      </ol>
      
      <!-- Wrapper for slides -->
      <div class="carousel-inner" role="listbox">
        <div class="item active" style="background-image: url(https://i.imgur.com/wXQhShM.jpg)">
          <div class="carousel-caption"></div>
        </div>
        <div class="item" style="background-image: url(https://i.imgur.com/MJufWLF.jpg)">
          <div class="carousel-caption"></div>
        </div>
        <div class="item" style="background-image: url(https://i.imgur.com/jF029Up.jpg)">
          <div class="carousel-caption"></div>
        </div>
      </div>
      
      <!-- Controls -->
      <a class="left carousel-control" href="#main-carousel" role="button" data-slide="prev">
        <i class="fa fa-chevron-left" aria-hidden="true"></i> 
        <span class="sr-only">Previous</span>
      </a> <a class="right carousel-control" href="#main-carousel" role="button" data-slide="next"> 
        <i class="fa fa-chevron-right" aria-hidden="true"></i> 
        <span class="sr-only">Next</span>
      </a>
    </div>

    <div class="container">
    
      <c:if test="${homeEvents.hasActiveEvents()}">
        <div class="row">
          <div class="col-xs-12">
            <div class="alert alert-info alert-active-event" role="alert">
              <div>
                <strong>Now Playing:</strong>
              </div>
              <div class="active-event-container">
                <c:forEach items="${homeEvents.activeEvents}" var="activeEvent">
                  <c:choose>
                     <c:when test="${not empty activeEvent.eventUrl}">
                       <a class="alert-link" href="${activeEvent.eventUrl}" target="_blank">
                         <span>${activeEvent.name}</span>
                          <c:if test="${not empty activeEvent.activeSchedule}">
                           <span>&#64; ${activeEvent.activeSchedule.place}</span>
                          </c:if>
                       </a>
                     </c:when>
                     <c:otherwise>${activeEvent.name}</c:otherwise>
                   </c:choose>
                   <br>
                </c:forEach>
              </div>
            </div>
          </div>
        </div>
      </c:if>
      
      
      <c:if test="${homeEvents.hasUpcomingEvents()}">
      
        <div class="row">
          <div class="col-xs-12">
            <h1>Upcoming Events:</h1>
          </div>
        </div>
        
        
        <c:forEach items="${homeEvents.upcomingEvents}" var="upcomingEvent">
          <div class="row event-container">
          
            <div class="col-xs-12">
              
              <div class="event">
                <div class="row">
                  <div class="col-sm-6 col-md-5 col-lg-4">
                  
                    <div class="event-info">
                  
                      <label class="event-name">
                        <c:choose>
                           <c:when test="${not empty upcomingEvent.eventUrl}">
                             <a href="${upcomingEvent.eventUrl}" target="_blank">${upcomingEvent.name}</a>
                           </c:when>
                           <c:otherwise>${upcomingEvent.name}</c:otherwise>
                         </c:choose>
                      </label>
                      
                      <p>${upcomingEvent.memo}</p>
                    
                    </div>
                   
                  </div>
                  
                  <div class="col-sm-6 col-md-7 col-lg-8">
                    <div class="row">
                      <c:forEach items="${upcomingEvent.schedules}" var="schedule">
                        <div class="col-xs-6 col-md-4 col-lg-3 schedule">
                          <div class="thumbnail">
                            <div class="caption">
                              <h3 class="date-title" data-date-time-stamp="${schedule.dateAsTimeStamp}"></h3>
                              
                              <dl>
                                <c:if test="${not empty schedule.place}">
                                  <dt>
                                    <span>Venue</span>
                                    <c:if test="${not empty schedule.memo}">
                                      <i class="fa fa-info-circle fa-lg" aria-hidden="true" data-toggle="tooltip" 
                                                                                      data-placement="bottom" 
                                                                                      data-container="body" 
                                                                                      data-html="true" 
                                                                                      title="${schedule.memo}"></i>
                                    </c:if>
                                    
                                    <c:if test="${not empty schedule.link}">
                                      <a href="${schedule.link}" target="_blank">
                                        <i class="fa fa-link fa-lg" aria-hidden="true"></i>
                                      </a>
                                    </c:if>
                                  </dt>
                                  <dd>
                                    <span>${schedule.place}</span>
                                    <c:if test="${not empty schedule.prefecture}">
                                      <span>(${schedule.prefecture})</span>
                                    </c:if>
                                  </dd>
                                </c:if>
                                <c:if test="${not empty schedule.enterTime}">
                                  <dt>Enter Time</dt>
                                  <dd>${schedule.enterTime}</dd>
                                </c:if>
                                <c:if test="${not empty schedule.startTime}">
                                  <dt>Start Time</dt>
                                  <dd>${schedule.startTime}</dd>
                                </c:if>
                              </dl>
                            </div>
                          </div>
                        </div>
                      </c:forEach>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </c:if>
      
    </div>

    <%@ include file="fragment/footer.jsp" %>
    
    <!-- Scripts -->
    <script src="${context}/js/vendor/jquery-3.3.1.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="${context}/js/home.js"></script>
  </body>

</html>