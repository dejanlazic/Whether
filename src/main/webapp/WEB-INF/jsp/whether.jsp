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

<!--
      IMPORTANT:
      This is Heroku specific styling. Remove to customize.
    -->
<link href="http://heroku.github.com/template-app-bootstrap/heroku.css" rel="stylesheet">
<!-- /// -->

</head>

<body>
   <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
         <div class="container">
         </div>
      </div>
   </div>

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
         </div>
      </div>
   </div>
</body>
</html>