<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Introduction Web Services</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
 <div class="container">
  <br>
  <hr>
  <br>
  <form action="rest/hello/helloText" method="get" id="helloJerseyText">
   Hello Jersey - TEXT
   <button type="submit">Send</button>
  </form>
  <br>
  <hr>
  <form action="rest/hello/helloXML" method="get" id="helloJerseyXML">
   Hello Jersey - XML
   <button type="submit">Send</button>
  </form>
  <br>
  <hr>
  <form action="rest/hello/helloJSON" method="get" id="helloJerseyJSON">
   Hello Jersey - JSON
   <button type="submit">Send</button>
  </form>
  <br>
  <hr>
  <h5>Compute your age.</h5>
  <form action="rest/AgeService" method="post" id="computeAgeForm">
   <div>
    <input type="text" id="birthdate" name="birthdate"
     placeholder="Type your birth date" />
   </div>
   <br> <br>
   <div>
    <button type="submit" class="btn btn-info">Send</button>
   </div>
  </form>
  <br>
  <hr>
  <br>
 </div>
</body>
</html>
