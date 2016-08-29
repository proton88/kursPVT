<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="by.my.library.domain.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="url" value="${requestScope.encodeURL}"/>
	<c:if test="${url==null}">
		<c:set var="url" value="Controller"/>
	</c:if>

<c:out value="Hello user "/>
<c:out value="${sessionScope.user.name}" />
<br/><br/>
<form action="${url}" method="post">
	<input type="hidden" name="command" value="show_books"/>
	<input type="submit" name="Show books" value="Show books"/>
</form>
<form action="${url}" method="post">
	<input type="hidden" name="command" value="findByTitle"/>
	В это поле вы можете ввести название книги для ее поиска.<br/>
	<input type="text" name="bookTitle">
	<input type="submit" name="Find book" value="Find book"/>
</form>
<br><br>
<c:if test="${sessionScope.user.role=='admin'}">
	<form action="${url}" method="post">
		<input type="hidden" name="command" value="addBook"/>
		Название книги<br/>
		<input type="text" name="title"><br>
		Автор<br/>
		<input type="text" name="author"><br>
		Жанр<br/>
		<input type="text" name="genre"><br>
		Цена<br/>
		<input type="text" name="price"><br>
		ISBN<br/>
		<input type="text" name="isbn"><br>
		<input type="submit" name="Add book" value="Add book"/>
	</form>
</c:if>
</body> 
</html> 