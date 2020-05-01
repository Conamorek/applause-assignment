# applause-assignment application

##Setup to start the application:

#####Requirements:
Java jdk 11 <br>
Possibly good to have npm/node installed, due to vulnerability of maven plugin installing it.
<br>
Maven wrapper is provided, so local maven installation is not needed.

#####Stack used:
Backend:
- Java 11 <br>
- Spring Boot newest version (2.2.6)
- Spring Data with Hibernate implementation
- Lombok
- H2 as an internal memory db used to persist data from CSV files

Frontend:
- Angular 9
- Bootstrap 4


#####Steps to install and run on local:
1 ) Run maven command from inside applause-assignment directory (below command for Linux, use mvnw.cmd for Windows):
- ./mvnw clean install -DskipTests

2 ) To startup backend side run below command from inside applause-assignment directory:
- ./mvnw spring-boot:run

3 ) To startup web server providing Angular frontend run below command from frontend directory:
- ng serve
<br>
<br>

4 ) Application is available under: <br>
 Frontend:<b> http://localhost:4200 </b>  <br>
 Backend: <b>http://localhost:8080</b>

#####Possible problems:
1. Npm error during compilation (Angular 9 and npm is not too stable) - try to delete whole node_modules and
package-lock.json. Try to run ./mvn clean install under frontend directory

2. Backend side doesn't compile or doesn't start - make sure you use jdk 11. 
