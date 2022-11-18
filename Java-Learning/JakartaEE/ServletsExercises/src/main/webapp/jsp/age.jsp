<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.Period"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>
<body>
 <%
 Period p = (Period) request.getAttribute("age");
 %>
 <h3>
  Your age is
  <%=p.getYears()%>
  years(s),
  <%=p.getMonths()%>
  month(s) and
  <%=p.getDays()%>
  day(s).
 </h3>
</body>
</html>