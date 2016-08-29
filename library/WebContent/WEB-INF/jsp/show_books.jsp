<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Список книг</title>
</head>
<body>
	<table border="1"><tr><td>№</td><td>Title</td><td>Author</td><td>Genre</td><td>Price</td><td>ISBN</td></tr>
	<c:forEach items="${requestScope.catalog}" var="book" varStatus="count">
		<tr>
			<td>
				<c:out value="${count.count}"/>
			</td>
			<td>
				<c:out value="${book.title}"/>
			</td>
			<td>
				<c:out value="${book.author}"/>
			</td>
			<td>
				<c:out value="${book.genre}"/>
			</td>
			<td>
				<c:out value="${book.price}"/>
			</td>
			<td>
				<c:out value="${book.isbn}"/>
			</td>
		</tr>
	</c:forEach>
	</table>
	<br/>
	<form action="${url}" method="post"></form>
</body>
</html>