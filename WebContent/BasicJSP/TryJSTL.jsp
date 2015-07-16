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
	<c:set var="DAYS" value="Monday,Tuesday,Wednesday,Thursday,Friday" />

	<!-- check if number is even -->
	<p>
		<c:set var="number" value="4" />
		<c:if test="${number%2==0 }">
			<c:out value="${number} is even" />
		</c:if>
	</p>

	<!-- print sqr of numbers -->
	<p>
		<c:forEach var="i" begin="1" end="5">
			<c:out value="${i}^2: ${i*i}" />
		</c:forEach>
	</p>

	<!-- print number and day of weeks -->
	<p>
		<c:set var="numOfDay" value="0" />
		<c:forTokens items="${DAYS}" delims="," var="day">
			<c:set var="numOfDay" value="${numOfDay+1}" />

			<c:if test="${i%numOfDay==0 }">
				<c:out value="${numOfDay} day of the week is ${day}" />
				<br>
			</c:if>
		</c:forTokens>
	</p>

	<!-- check if u can rest today -->
	<p>
		<c:set value="8" var="numOfDay" />
		<c:choose>
			<c:when test="${numOfDay <= 5 && numOfDay > 0}">
       Is Weekday
    </c:when>
			<c:when test="${numOfDay > 5 && numOfDay <= 7}">
        Is Weekend
    </c:when>
			<c:otherwise>
        <h1>Magick</h1>
    </c:otherwise>
		</c:choose>
	</p>

	<!-- include servlet page with parameters specified -->
	<p>
		Imported servlet with params: <br>
		<c:url value="../WithParams" var="mURL">
			<c:param name="first_name" value="test" />
			<c:param name="last_name" value="name" />
		</c:url>
		<c:import url="${mURL}" />
	</p>
</body>
</html>