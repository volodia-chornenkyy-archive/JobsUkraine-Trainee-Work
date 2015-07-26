<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Session Scope (key==values)</h3>
	<%
		java.util.Enumeration<String> sessEnum = request.getSession().getAttributeNames();
		while (sessEnum.hasMoreElements()) {
			String s = sessEnum.nextElement();
			if (s.contains("myObj")) {
				out.print("<b>" + s + "==" + request.getSession().getAttribute(s) + "</b><br>");
			} else {
				out.print(s + "==" + request.getSession().getAttribute(s) + "<br>");
			}
		}
	%><br />
</body>
</html>