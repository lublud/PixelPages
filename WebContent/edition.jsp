<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">
<title>Pixel Pages - Edition</title>
<script type="text/javascript" language="javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
</head>

<body>
	<%@include file="header.jsp"%>
	<div id="content">
		<form method="post" action="controller" id="form">
			<fieldset id="edit">
				<legend>Edition</legend>
				
				<table>
					<tr>
						<td>First Name</td>
						<td><c:out value="${user.getFirstName()}" /></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><c:out value="${user.getLastName()}" /></td>
					</tr>
					<tr>
						<td>Birth date</td>
						<td><fmt:formatDate dateStyle="long" value="${user.getBirthdate()}" /></td>
					</tr>
					<tr>
						<td>Web site</td>
						<td><input type="text" name="webSite" value="<c:out value="${user.getWebsite()}" />"
								size="16" maxlength="128" /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value="<c:out value="${user.getEmail()}" />"
								size="16" maxlength="128" /></td>
					</tr>
					</table>
					
					<br>
					<div id="change">
						<a href="javascript:;" onClick="$('#PassHider').toggle('slow');"><b>↓ Change your password ↓</b></a>
					</div>
					<br>
					<div id="PassHider">
					<table>
						<tr>
							<td>Old password</td>
							<td><input type="password" name="oldPasswd" value="" size="16" maxlength="32" /></td>
						</tr>
						<tr>
							<td>New password</td>
							<td><input type="password" name="newPasswd" size="16" maxlength="32" /></td>
						</tr>
						<tr>
							<td>Repeat new password</td>
							<td><input type="password" name="newPasswdBis" value="" size="16" maxlength="32" /></td>
						</tr>
					</table>
					
					<br>
					<b>Rules for passwords</b>
					<ul>
						<li> It must contains at least one upper case</li> 
						<li> It must contains at least one lower case</li> 
						<li> It must contains at least one number</li> 
						<li> It must contains at least one of the following character: @, #, $, %, !, /</li> 
						<li> It must be between 8 and 32 characters long</li> 
					</ul>
					</div>
				
				<input type="submit" value="Modify" />
			</fieldset>
		<br>
		<div id="error">
			<c:out value="${error}" />
			<c:remove var="error" scope="session" />
		</div>
		
		</form>
	</div>
</body>
</html>