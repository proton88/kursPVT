<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Library</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.locale }"/>
	<fmt:setBundle basename="resources.localization" var="loc"/>
	<fmt:message bundle="${loc}" key="localization.ru_button" var="ru_button"/>
	<fmt:message bundle="${loc}" key="localization.en_button" var="en_button"/>
	<fmt:message bundle="${loc}" key="index.message" var="message"/>
	<fmt:message bundle="${loc}" key="index.input_login" var="login"/>
	<fmt:message bundle="${loc}" key="index.input_password" var="password"/>
	<fmt:message bundle="${loc}" key="index.button_login" var="but_login"/>
	<fmt:message bundle="${loc}" key="index.message_reg" var="reg_message"/>
	<fmt:message bundle="${loc}" key="index.button_reg" var="but_reg"/>
	<c:if test="${error!=null}">
		<fmt:message bundle="${loc}" key="${error}" var="error_message"/>
	</c:if>
	<c:set var="url" value="${requestScope.encodeURL}"/>
	<c:if test="${url==null}">
		<c:set var="url" value="Controller"/>
	</c:if>
	<form action="${url}" method="post">
		<input type="hidden" name="command" value="locale">
		<input type="hidden" name="locale" value="ru">
		<input type="submit" value="${ru_button}">
	</form>
	<form action="${url}" method="post">
		<input type="hidden" name="command" value="locale">
		<input type="hidden" name="locale" value="en">
		<input type="submit" value="${en_button}">
	</form>
	<c:set var="messageRequest" value="${requestScope.error}"/>
	<c:if test="${messageRequest!=null}">
		<c:out value="${error_message}"/>
	</c:if>
	<c:if test="${messageRequest==null}">
		<c:out value="${message}"/>
	</c:if>
	
	<form action="${url}" method="post">
		<input type="hidden" name="command" value="logination">
		${login}<br/>
		<input type="text" name="login" value="${cookie.login.value}"><br/>
		${password}<br/>
		<input type="password" name="password" value="${cookie.password.value}"><br/>
		<input type="submit" value="${but_login}"/>
	</form>
	<br>
	${reg_message}<br>
	<form action="reg" method="post">
		<input type="submit" value="${but_reg}"> 
	</form>
</body>
</html>