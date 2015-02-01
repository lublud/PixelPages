<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">
<title>Pixel Pages - Details person</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div id="content">
		<table id="element">
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
			<c:if test="${user != null}">
				<tr>
					<td><b>email</b></td>
					<td><c:out value="${person.getEmail()}" /></td>
				</tr>
				<tr>
					<td><b>Birth date</b></td>
					<td><fmt:formatDate type="date" dateStyle="long"
							value="${person.getBirthdate()}" /></td>
				</tr>
			</c:if>

		</table>
	</div>
</body>
</html>