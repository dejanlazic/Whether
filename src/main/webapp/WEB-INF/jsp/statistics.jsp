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
               <h1>Statistics</h1>
            </div>
            
            <a href="/whether/" class="btn btn-primary">Home</a>
            
            <c:if test="${!empty citiesList}">
               <table class="table table-bordered table-striped">
                  <thead>
                     <tr>
                        <th style="width: 50%;">City</th>
                        <th style="width: 50%;">Hits</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="city" items="${citiesList}">                  
                        <tr>
                           <td><c:out value="${city.name}"/></td>
                           <td><c:out value="${city.counter}"/></td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>                                                                                
            </c:if>   
         </div>
      </div>
   </div>
</body>
</html>