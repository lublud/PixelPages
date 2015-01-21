<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">
<title>Pixel Pages - Edition</title>
<%@include file="header.jsp"%>
</head>
<body>
	<div id="content">
		<fieldset>
			<legend>Edition</legend>
			<c:out value="${error}" />
			<c:remove var="error" scope="session" />
			<form method="post" action="controller">
				<p>
					<label for="firstName">First Name:</label>
					<input type="text" name="firstName" value="<c:out value="${user.getFirstName()}" />"
								size="16" maxlength="64" />
					
					<br />
					<label for="lastName">Last Name:</label>
					<input type="text" name="lastName" value="<c:out value="${user.getLastName()}" />"
								size="16" maxlength="64" />
	
					<br />
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<label for="birthdate">Birth date (format: <fmt:formatDate pattern="dd/MM/yyyy" 
								value="${now}" />):</label>
					<input type="text" name="birthdate" value="<fmt:formatDate pattern="dd/MM/yyyy" 
								value="${user.getBirthdate()}" />" size="16" maxlength="10" />
					
					<br />
					<label for="webSite">Web site:</label>
					<input type="text" name="webSite" value="<c:out value="${user.getWebsite()}" />"
								size="16" maxlength="128" />
					
					<br />
					<label for="email">email:</label>
					<input type="text" name="email" value="<c:out value="${user.getEmail()}" />"
								size="16" maxlength="128" />
					
					<br />
					<input type="submit" value="OK!" />
				</p>
			</form>
		</fieldset>
	</div>
</body>
</html>