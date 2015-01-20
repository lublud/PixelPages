<%@ page import="java.util.List"%>
<%@ page import="fr.univamu.master.jee.exam.beans.Person"%>
<%@ page import="fr.univamu.master.jee.exam.dao.DAO"%>
<%@ page import="fr.univamu.master.jee.exam.dao.concret.PersonDAO"%>
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

			<%
				DAO dao = new PersonDAO();
				dao.init();
				List<Person> list = dao.findAllPersons();
			%>
			<%
				for (int i = 0; i < list.size(); ++i) {
					out.println("<tr>");

					out.println("<td>");
					out.println(list.get(i).getFirstName());
					out.println("</td>");

					out.println("<td>");
					out.println(list.get(i).getLastName());
					out.println("</td>");

					out.println("</tr>");
				}
			%>

		</table>
		<%
			dao.close();
		%>
	</center>
</body>
</html>