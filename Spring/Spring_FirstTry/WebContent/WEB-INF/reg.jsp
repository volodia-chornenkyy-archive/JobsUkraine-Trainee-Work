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
	reg

	<form:form method="POST" action="/requestbody">
		<input type="submit" name="action" id="request_body_test" value="<spring:message code="label.request.body"/>"  />
		<input type="hidden" name="id" value="23" />
		<input type="hidden" name="group" value="male" />
	</form:form>

</body>
</html>