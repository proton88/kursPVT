<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Регистрация</title>
</head>
<body>
	<c:if test="${error!=null}">
		<c:out value="${error}"/>
	</c:if>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration">
		Login*:<br/>
		<input type="text" name="login"><br/>
		Пароль*:<br/>
		<input type="password" name="password"><br/>
		Повторите пароль*:<br/>
		<input type="password" name="passwordRepeat"><br/>
		Имя:<br/>
		<input type="text" name="name"><br/>
		Фамилия:<br/>
		<input type="text" name="surname"><br/>
		Адресс:<br/>
		<input type="text" name="adress"><br/>
		Номер паспорта*:<br/>
		<input type="text" name="passportId"><br/>
		<input type="submit" value="зарегистрироваться"/>
	</form>
</body>
</html>