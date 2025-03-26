<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--부트스트랩 CDN-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

<style>
	body {
		margin: auto;
		width: 800px;
	}
	a {
		text-decoration:none; color:black; cursor:pointer;
	}
</style>
</head>
<body>
	<h1 align="center">게 시 판</h1>
	<p>총 레코드수 : ${totalRecord}</p>
	<table class="table">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="b" items="${list}">
			<tr>
				<td>${b.boardno}</td>
				<td><a href="detail?boardno=${b.boardno}">${b.title}</td>
				<td>${b.writer}</td>
				<td><button class="btn btn-outline-danger" type="button">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<a href="writeForm"><button type="button" value="글작성">글작성</button></a>
</body>
</html>