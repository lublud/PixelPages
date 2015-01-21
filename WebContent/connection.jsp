<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pixel Pages - Connection</title>
<%@include file="header.jsp"%>
</head>
<body>
	<div id="content">
		<form method="post" action="connection">
			<fieldset>
				<legend>Connection</legend>
				<!-- if there is an error, print it and then remove it -->
				<c:out value="${error}" />
				<c:remove var="error" scope="session" />
				<p>
					<label for="login">Login</label><input type="text" name="login"
						value="" size="16" maxlength="16" />
				</p>
				<p>
					<label for="password">Password</label><input class="mandatory"
						type="password" name="password" size="16" maxlength="32" />
				</p>
				<p>
					<input class="button" type="submit" name="OK" value="ok" />
				</p>
			</fieldset>
		</form>
	</div>
</body>
</html>