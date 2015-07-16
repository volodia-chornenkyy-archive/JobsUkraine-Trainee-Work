<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Navigation</h1>
	</center>
	<p>
	<h3>Servlets:</h3>
	<ul>
		<li><a href="/Servlets/SimpleOne">Check if servlet
				successfully running (SimpleGet.java)</a></li>
		<li><a href="/Servlets/AIO">Check lifecycle of servlet
				(AllInOne.java)</a></li>
		<li><a
			href="/Servlets/WithParams?first_name=i+can+test+it&last_name=all+ok">Check
				work of doGet (WithParams.java)</a></li>
	</ul>
	</p>
	<p>
	<h3>JSP:</h3>
	<ul>
		<li><a href="/Servlets/TryDirectives.jsp">Try of jsp
				directives (TryDirectives.jsp)</a></li>
		<li><a href="/Servlets/Form.jsp">Check doPost with JavaBeans
				(Form.jsp, Result.jsp, pckg/Obj.java)</a></li>
		<li><a href="/Servlets/TryJSTL.jsp">Try work of jstl core
				taglib (TryJSTL.java)</a></li>
		<li><a href="/Servlets/FilterTest/FilterStartup.jsp">Check
				work of redirect/forward by filter (/FilterTest/FilterStartup.jsp,
				/FilterTest/Error.jsp, filters/TryFilter.java)</a></li>
	</ul>
	</p>
</body>
</html>