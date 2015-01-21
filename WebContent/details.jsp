<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">
<title>Pixel Pages - Details person</title>
<%@include file="header.jsp"%>
</head>
<body>
	<div id="content">
		<table>
			<tr>
				<td><b>First name</b></td>
				<td><c:out value="${person.getFirstName()}" /></td>
			</tr>
			<tr>
				<td><b>Last name</b></td>
				<td><c:out value="${person.getLastName()}" /></td>
			</tr>
			<tr>
				<td><b>Web site</b></td>
				<td><c:out value="${person.getWebsite()}" /></td>
			</tr>
		</table>
	</div>
</body>
</html>