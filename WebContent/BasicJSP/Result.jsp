<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="usr" class="servlets.Obj" />
<jsp:setProperty property="*" name="usr" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	You entered
	<BR> String:
	<jsp:getProperty property="s" name="usr"/>
	<br> Integer:
	<jsp:getProperty property="i" name="usr"/>

</body>
</html>