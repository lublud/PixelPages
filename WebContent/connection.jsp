<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./CSS/style.css">
<title>Pixel Pages - Connection</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<div id="content">
		<form id="element" method="post" action="connection">
			<fieldset>
				<legend>Connection</legend>
				<table id="form">
					<tr>
						<td>Login</td>
						<td><input type="text" name="login" value="" size="16" maxlength="16" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input class="mandatory" type="password" name="password" size="16" maxlength="32" /></td>
					</tr>
				</table>
				<br><input class="button" type="submit" name="OK" value="Connect" />
			</fieldset>
			<div id="error">
				<c:out value="${error}" />
				<c:remove var="error" scope="session" />
			</div>
		</form>
	</div>
</body>
</html>