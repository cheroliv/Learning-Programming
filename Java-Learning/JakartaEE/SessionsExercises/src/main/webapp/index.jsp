<%@page import="java.util.stream.Stream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Introduction Sessions</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%
	String         login    = "";
	String         password = "";
	Cookie[]       cookies  = request.getCookies();

	if (cookies != null) {
	  Cookie cookie = Stream.of(cookies).filter(c -> c.getName().equals("cookie_auth")).findFirst().orElse(null);
	  login          = (cookie != null) ? cookie.getValue().split("_")[0] : login;
	  password       = (cookie != null) ? cookie.getValue().split("_")[1] : password;
	}
	%>
	<div id="wrapper_main">
		<h3>Test Session</h3>
		<form action="/TestsSessions/Cookies" method="post"
			id="authentificationForm" role="form" aria-label="FormAuth">
			<div>
				<input type="text" id="login" name="login" class="form-control"
					value="<%=login%>" placeholder="Enter your login" />
			</div>
			<br>
			<div>
				<input type="password" id="password" name="password"
					value="<%=password%>" class="form-control"
					placeholder="Enter your password" />
			</div>
			<br>
			<div>
				<input type="checkbox" id="signed" name="signed"
					class="form-control" value="signed" /> Save credentials
			</div>
			<br>
			<div>
				<button type="submit" class="btn btn-info">Sign in</button>
			</div>
		</form>
		<br>
		<div>
			Connected:
			<%
		String log = (String) request.getAttribute("connected");
		out.println((log != null) ? log : "");
		%>
		</div>
	</div>
</body>
</html>
