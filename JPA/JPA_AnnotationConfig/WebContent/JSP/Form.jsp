<%@page import="model.Figure"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Points service</h1>
	<form action="/JPA_AnnotationConfig/PointTest" method="post">
		<p>
			Figure type: <select name="f">
				<%
					for (Figure f : Figure.values()) {
						out.println("<option>" + f.toString() + "</option>");
					}
				%>
			</select>
		</p>
		<p>
			Coordinates: <br> x: <input name="x" value="0.0"> y: <input name="y" value="0.0">
			z: <input name="z" value="0.0">
		</p>
		<p>
			<input type="submit" name="Add">
		</p>
	</form>
</body>
</html>