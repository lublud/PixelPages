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
					<c:out value="${user.getFirstName()}" />
					
					<br />
					<label for="lastName">Last Name:</label>
					<c:out value="${user.getLastName()}" />
	
					<br />
					<label for="birthdate">Birth date:</label>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${user.getBirthdate()}" />
					
					<br />
					<label for="webSite">Web site:</label>
					<input type="text" name="webSite" value="<c:out value="${user.getWebsite()}" />"
								size="16" maxlength="128" />
					
					<br />
					<label for="email">email:</label>
					<input type="text" name="email" value="<c:out value="${user.getEmail()}" />"
								size="16" maxlength="128" />
								
					<br />
					<label for="oldPasswd">Old password:</label>
					<input type="password" name="oldPasswd" value="" size="16" maxlength="32" />
								
					<br />
					<label for="newPasswd">New password:</label>
					<input type="password" name="newPasswd" size="16" maxlength="32" />
								
					<br />
					<label for="newPasswdBis">New password (repeat):</label>
					<input type="password" name="newPasswdBis" value="" size="16" maxlength="32" />
					
					<br />
					<input type="submit" value="OK!" />
				</p>
			</form>
		</fieldset>
		<p>
			Rules for passwords:
			<ul> 
				<li> It must contains at least one upper case</li> 
				<li> It must contains at least one lower case</li> 
				<li> It must contains at least one number</li> 
				<li> It must contains at least one of the following character: @, #, $, %, !, /</li> 
				<li> It must be between 8 and 32 characters long</li> 
			</ul>
		</p>
	</div>
</body>
</html>