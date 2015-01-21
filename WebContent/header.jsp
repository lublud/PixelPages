<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">
</head>
<body>
	<c:url var="home" value="./directory.jsp" />
	<div id="header">
		<img id="top_left" src="./images/logoamu.png"
			alt="Aix-Marseille Université"
			style="height: auto; width: auto; max-width: 250px;" />
		<a id="top_center" href="${home}"><h2>Pixel Pages</h2> </a>
		<span id="top_right">
			<c:choose>
				<c:when test="${user == null}">
					<a href="./connection.jsp">connection</a>
				</c:when>
				<c:when test="${user != null}">
					<c:out value="Welcome ${user.getLastName()}!" />
					<a href="./edition.jsp">profile</a>
					<a href="./disconnection.jsp">disconnect</a>
				</c:when>
			</c:choose>
		</span>
		<!-- if connected: person's name, else: connection -->

	</div>
</body>
</html>
