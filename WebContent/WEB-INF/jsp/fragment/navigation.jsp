<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="userLoggedIn" value="${not empty soilAndPimpSessionBean.user}"/>

<nav class="navbar navbar-inverse">
  <div class="container">

    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-navbar" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span> 
        <span class="icon-bar"></span> 
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/"> 
        <img alt="Brand" src="https://i.imgur.com/O0efPKV.png" />
      </a>
      <p class="navbar-text">SOIL &amp; "PIMP" LIVE</p>
    </div>

    <div class="collapse navbar-collapse" id="main-navbar">
      <ul class="nav navbar-nav">
        <li id="home">
          <a href="/">Home</a>
        </li>
        <li id="past-events">
          <a href="/past-events">Past Events</a>
        </li>
        <c:if test="${userLoggedIn}">
          <li id="maintenance">
            <a href="/admin/maintenance">Maintenance</a>
          </li>
        </c:if>
      </ul>

      <form class="navbar-form navbar-right" role="search">
        <a type="button" class="btn btn-default" id="subscribe">
          <i class="fa fa-envelope" aria-hidden="true"></i>
          <span>Subscribe</span>
        </a>
      </form>

    </div>
  </div>
</nav>