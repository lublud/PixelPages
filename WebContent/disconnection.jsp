<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta HTTP-EQUIV="Refresh"
CONTENT="2; URL=./directory.jsp"> 
<title>Pixel Pages - disconnection</title>
</head>
<body>
	<div id="content">
		<!-- if there is an error, print it and then remove it -->
		<c:remove var="user" scope="session" />
		<c:out value="Please, while while being disconnected..." />
	</div>
</body>
</html>