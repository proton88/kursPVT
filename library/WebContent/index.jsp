<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Library</title>
</head>
<body>
	<c:set var="url" value="${requestScope.encodeURL}"/>
	<c:if test="${url==null}">
		<c:set var="url" value="Controller"/>
	</c:if>
	<!--<c:out value="${url}"/>-->
	
	<!--<c:set var="messageSession" value="${sessionScope.message}"/>
	<c:if test="${messageSession!=null}">
		<c:out value="${messageSession}"/>
	</c:if>-->
	
	<c:set var="messageRequest" value="${requestScope.error}"/>
	<c:if test="${messageRequest!=null}">
		<c:out value="${messageRequest}"/>
	</c:if>
	<c:if test="${messageRequest==null}">
		<c:out value="Пожалуйста, авторизуйтесь"/>
	</c:if>
	<form action="${url}" method="post">
		<input type="hidden" name="command" value="logination">
		Login:<br/>
		<input type="text" name="login" value="${cookie.login.value}"><br/>
		Password:<br/>
		<input type="password" name="password" value="${cookie.password.value}"><br/>
		<input type="submit" value="войти"/>
	</form>
	<br>
	Если вы не зарегистрированы, <br>
	пожалуйста, зарегистрируйтесь!<br>
	<form action="reg.jsp" method="post">
		<input type="submit" value="зарегистрироваться"> 
	</form>
</body>
</html>