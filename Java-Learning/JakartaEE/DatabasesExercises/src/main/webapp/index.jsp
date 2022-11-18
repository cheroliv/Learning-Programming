<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Test H2 Database</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
 <div id="wrapper_main">
  <h3>Display players.</h3>
  <form action="/TestsDatabases/PlayerServlet" method="post" id="displayPlayerForm" role="form" aria-label="FormPlayer">
   <button type="submit">Show</button>
  </form>
 </div>
</body>
</html>
