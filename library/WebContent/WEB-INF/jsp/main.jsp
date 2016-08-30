<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="by.my.library.domain.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Кабинет</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.locale }"/>
	<fmt:setBundle basename="resources.localization" var="loc"/>
	<fmt:message bundle="${loc}" key="main.hello" var="hello"/>
	<fmt:message bundle="${loc}" key="main.button_show_books" var="show_books"/>
	<fmt:message bundle="${loc}" key="main.mes_book_title" var="book_title"/>
	<fmt:message bundle="${loc}" key="main.button_find_book" var="find_book"/>
	<fmt:message bundle="${loc}" key="main.input_title" var="title"/>
	<fmt:message bundle="${loc}" key="main.input_author" var="author"/>
	<fmt:message bundle="${loc}" key="main.input_genre" var="genre"/>
	<fmt:message bundle="${loc}" key="main.input_price" var="price"/>
	<fmt:message bundle="${loc}" key="main.button_add_book" var="add_book"/>
	
<c:set var="url" value="${requestScope.encodeURL}"/>
	<c:if test="${url==null}">
		<c:set var="url" value="Controller"/>
	</c:if>

<c:out value="${hello}"/>
<c:out value="${sessionScope.user.name}" />
<br/><br/>
<form action="${url}" method="post">
	<input type="hidden" name="command" value="show_books"/>
	<input type="submit" name="Show books" value="${show_books}"/>
</form>
<form action="${url}" method="post">
	<input type="hidden" name="command" value="findByTitle"/>
	${book_title}<br/>
	<input type="text" name="bookTitle">
	<input type="submit" name="Find book" value="${find_book}"/>
</form>
<br><br>
<c:if test="${sessionScope.user.role=='admin'}">
	<c:set var="mes" value="${message}"/>
	<form action="${url}" method="post">
		<input type="hidden" name="command" value="add_Book"/>
		${title}<br/>
		<input type="text" name="title"><br>
		${author}<br/>
		<input type="text" name="author"><br>
		${genre}<br/>
		<input type="text" name="genre"><br>
		${price}<br/>
		<input type="text" name="price"><br>
		ISBN<br/>
		<input type="text" name="isbn"><br>
		<input type="submit" name="Add book" value="${add_book}"/>
		${mes}
	</form>
</c:if>
</body> 
</html> 