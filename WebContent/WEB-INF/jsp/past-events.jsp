<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <%@ include file="fragment/head-block.jsp" %>
    
    <title>SOIL &amp; "PIMP" LIVE - Past Events</title>

    <link href="${context}/css/event-container.css" rel="stylesheet">
  </head>
  
  <body data-active-tab="past-events">
  
    <%@ include file="fragment/navigation.jsp" %>

    <div class="container">
    
      <div class="row">
        <div class="col-xs-12">
          <h1>Past Events &#40;${pastEvents.size()}&#41;</h1>
        </div>
      </div>
        
      <c:forEach items="${pastEvents}" var="pastEvent">
        <div class="row event-container">
        
          <div class="col-xs-12">
            
            <div class="event content-loading">
              <div class="row">
                <div class="col-sm-6 col-md-5 col-lg-4">
                
                  <div class="event-info">
                
                    <label class="event-name">
                      <c:choose>
                         <c:when test="${not empty pastEvent.eventUrl}">
                           <a href="${pastEvent.eventUrl}" target="_blank">${pastEvent.name}</a>
                         </c:when>
                         <c:otherwise>${pastEvent.name}</c:otherwise>
                       </c:choose>
                    </label>
                    
                    <p>${pastEvent.memo}</p>
                  
                  </div>
                 
                </div>
                
                <div class="col-sm-6 col-md-7 col-lg-8">
                  <div class="row">
                    <c:forEach items="${pastEvent.schedules}" var="schedule">
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
      
    </div>

    <%@ include file="fragment/footer.jsp" %>
    
    <!-- Modals -->
    <%@ include file="fragment/global-modals.jsp" %>
    
    <!-- Scripts -->
    <%@ include file="fragment/global-javascript.jsp" %>
    <script src="${context}/js/past-events.js?v=${projectVersion}"></script>
  </body>

</html>