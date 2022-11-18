<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="fr.chalodss.models.Player"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%-- <%@ page isELIgnored="false"%> --%>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Player H2 Database</title>
</head>
<body>
 <div id="wrapper_main">
  <h2>List of players</h2>
  <hr />
  <div>
   <table>
    <caption></caption>
    <thead>
     <tr>
      <th scope="col">Id</th>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Elo</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach var="player" items="${players}">
      <tr>
       <td><c:out value="${player.getId()}" /></td>
       <td><c:out value="${player.getFirstName()}" /></td>
       <td><c:out value="${player.getLastName()}" /></td>
       <td><c:out value="${player.getElo()}" /></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>
</body>
</html>