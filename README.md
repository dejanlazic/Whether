Project name:	Whether?

Description:

Whether is a web services client, which collects weather and geographical information 
for the provided city name. The application is a web application written in Java programming language 
using Spring MVC, running on Tomcat web container.

All necessary data for displaying is retrieved from the following RESTful web services:

World Weather Free Local Weather 
http://www.worldweatheronline.com/

GeoNames Web Services 
http://www.geonames.org


## Running the application locally

First build with:

    $mvn clean install

Then run it with:

    $java -jar target/dependency/webapp-runner.jar target/*.war