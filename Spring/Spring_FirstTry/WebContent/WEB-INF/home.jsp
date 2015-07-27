<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Home</h1>
	</center>

	<ul>
		<li><c:url value="/log" var="url1" /> <a
			href="<c:out value='${url1}'/>">Log In</a></li>
		<li><c:url value="/reg" var="url2" /> <a
			href="<c:out value='${url2}'/>">Request/Response Body (dont work)</a></li>
		<li><c:url value="/hit" var="url3" /> <a
			href="<c:out value='${url3}'/>">Cookies</a></li>
		<li><c:url value="/model" var="url4" /> <a
			href="<c:out value='${url4}'/>">@ModelAttribute @SessionAttributes</a></li>
		<li><c:url value="/employee/create" var="url5" /> <a
			href="<c:out value='${url5}'/>">DB connection</a></li>
	</ul>
</body>
</html>