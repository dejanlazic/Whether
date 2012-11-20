<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<title>Whether?</title>

<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
<link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">

<link href="http://heroku.github.com/template-app-bootstrap/heroku.css" rel="stylesheet">

</head>

<body>
   <div class="container">
      <div class="row">
         <div class="span8 offset2">
            <div class="page-header">
               <h1>Whether?</h1>
            </div>
            
            <form:form method="get" action="retrieve" commandName="city" class="form-vertical">
               <form:label path="name">City</form:label>
               <form:input path="name" />
                              
               <input type="submit" value="Submit" class="btn" />
            </form:form>
            
            <c:if test="${!empty dataList}">
               <c:forEach var="weatherData" items="${dataList}" varStatus="counter">
                  <c:choose>
                     <c:when test="${counter.first}">
                        <table class="table table-bordered table-striped">
                           <thead>
                              <tr>
                                 <th>Current conditions</th>
                                 <th>&nbsp;</th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr>
                                 <td>Pressure</td>
                                 <td><c:out value="${weatherData.pressure}"/></td>
                              </tr>
                              <tr>
                                 <td>Temperature (C)</td>
                                 <td><c:out value="${weatherData.tempC}"/></td>
                              </tr>
                              <tr>
                                 <td>Temperature (F)</td>
                                 <td><c:out value="${weatherData.tempF}"/></td>
                              </tr>                              
                              <tr>
                                 <td>Visibility</td>
                                 <td><c:out value="${weatherData.visibility}"/></td>
                              </tr>                              
                              <tr>
                                 <td>Humidity</td>
                                 <td><c:out value="${weatherData.humidity}"/></td>
                              </tr>                              
                              <tr>
                                 <td>Description</td>
                                 <td><b></b><c:out value="${weatherData.weatherDesc}"/></b></td>
                              </tr>                                                                                          
                           </tbody>
                        </table>                        
                     </c:when>
                     <c:otherwise>
                        <c:out value="${weatherData.date}" /> <br />
                     </c:otherwise>
                  </c:choose>
               </c:forEach>               
            </c:if>            
         </div>
      </div>
   </div>
</body>
</html>