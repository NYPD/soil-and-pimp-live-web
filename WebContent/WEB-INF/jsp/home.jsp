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
    <link href="${context}/css/home.css" rel="stylesheet">
  </head>
  
  <body>
  
    <nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <a class="navbar-brand">
            <img alt="Brand" src="https://i.imgur.com/O0efPKV.png" />
          </a>
          <p class="navbar-text">SOIL &amp; "PIMP" LIVE</p>
        </div>
        <form class="navbar-form navbar-right" role="search">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="email@email.com">
          </div>
          <a type="button" class="btn btn-default">Subscribe</a>
        </form>
      </div>
    </nav>

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
    
      <div class="row">
      
        <c:if test="${homeEvents.hasActiveEvents()}">
          <div class="col-xs-12">
  
            <h1>Active Events:</h1>
  
            <div class="table-responsive">
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th></th>
                    <th>Event Name</th>
                    <th class="text-right">Schedule Count</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${homeEvents.activeEvents}" var="activeEvent">
                    <tr>
                      <td></td>
                      <td>
                        <c:choose>
                          <c:when test="${not empty activeEvent.eventUrl}">
                            <a href="${activeEvent.eventUrl}" target="_blank">${activeEvent.name}</a>
                          </c:when>
                          <c:otherwise>${activeEvent.name}</c:otherwise>
                        </c:choose>
                      </td>
                      <td class="text-right">${activeEvent.schedules.size()}</td>
                    </tr>
  
                    <c:forEach items="${activeEvent.schedules}" var="schedule">
                      <tr>
                        <td colspan="3">${schedule.date}</td>
                      </tr>
                    </c:forEach>
  
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </c:if>

        <c:if test="${homeEvents.hasUpcomingEvents()}">
          <div class="col-sm-6">
  
            <h1>Upcoming Events:</h1>
  
            <div class="table-responsive">
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th></th>
                    <th>Event Name</th>
                    <th class="text-right">Scheduled</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${homeEvents.upcomingEvents}" var="upcomingEvent">
                    <tr>
                      <td></td>
                      <td>
                        <c:choose>
                          <c:when test="${not empty upcomingEvent.eventUrl}">
                            <a href="${upcomingEvent.eventUrl}" target="_blank">${upcomingEvent.name}</a>
                          </c:when>
                          <c:otherwise>${upcomingEvent.name}</c:otherwise>
                        </c:choose>
                      </td>
                      <td class="text-right">${upcomingEvent.schedules.size()}</td>
                    </tr>
                    <tr class="schedule-container">
                      <td colspan="3">
                        <div class="row row-schedule">
                          <c:forEach items="${upcomingEvent.schedules}" var="schedule">
                            <div class="col-sm-6 col-lg-4 schedule">
                              <div class="thumbnail">
                                <div class="caption">
                                  <h3 class="date-title" data-date-time-stamp="${schedule.dateAsTimeStamp}"></h3>
                                  
                                  <dl>
                                    <c:if test="${not empty schedule.place}">
                                      <dt>Venue</dt>
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
                      </td>
                    </tr>
  
                  </c:forEach>
                </tbody>
              </table>
            </div>
  
          </div>
        </c:if>

        <c:if test="${homeEvents.hasPastEvents()}">
          <div class="col-sm-6">
  
            <h1>Past Events:</h1>
  
            <div class="table-responsive">
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th></th>
                    <th>Event Name</th>
                    <th class="text-right">Scheduled</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${homeEvents.pastEvents}" var="pastEvent">
                    <tr>
                      <td></td>
                      <td>
                        <c:choose>
                          <c:when test="${not empty pastEvent.eventUrl}">
                            <a href="${pastEvent.eventUrl}" target="_blank">${pastEvent.name}</a>
                          </c:when>
                          <c:otherwise>${pastEvent.name}</c:otherwise>
                        </c:choose>
                      </td>
                      <td class="text-right">${pastEvent.schedules.size()}</td>
                    </tr>
                    <tr class="schedule-container">
                      <td colspan="3">
                        <div class="row row-schedule">
                          <c:forEach items="${pastEvent.schedules}" var="schedule">
                            <div class="col-sm-6 col-lg-4 schedule">
                              <div class="thumbnail">
                                <div class="caption">
                                  <h3 class="date-title" data-date-time-stamp="${schedule.dateAsTimeStamp}"></h3>
                                  
                                  <dl>
                                    <c:if test="${not empty schedule.place}">
                                      <dt>Venue</dt>
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
                      </td>
                    </tr>
  
                  </c:forEach>
                </tbody>
              </table>
            </div>
  
          </div>
        </c:if>
      </div>
    </div>

    <footer class="footer">
      <div class="container">
        <p class="text-muted">Place sticky footer content here.</p>
      </div>
    </footer>
    
    <!-- Scripts -->
    <script src="${context}/js/vendor/jquery-3.3.1.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="${context}/js/home.js"></script>
  </body>

</html>