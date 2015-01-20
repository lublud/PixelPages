<%@ page import="java.util.List"%>
<%@ page import="fr.univamu.master.jee.exam.beans.Person"%>
<%@ page import="fr.univamu.master.jee.exam.dao.DAO"%>
<%@ page import="fr.univamu.master.jee.exam.dao.concret.PersonDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./directory.css">
<title>Pixel Pages - Directory</title>
<%@include file="header.jsp"%>
</head>
<body>

	<center>
		<table>
			<tr>
				<td><b>First Name</b></td>
				<td><b>Last Name</b></td>
			</tr>
			

			<jsp:useBean id="ListPerson" scope="page" class="fr.univamu.master.jee.exam.beans.ListPerson" />
			<c:forEach var="person" items="${ListPerson.getListPerson()}">
				<tr>
					<td>
						<c:out value="${person.getFirstName()}" />
					</td>
					<td>
						<c:out value="${person.getLastName()}" />
					</td>			
				</tr>
			</c:forEach>


		</table>
	</center>
</body>
</html>