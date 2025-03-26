<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.detail{
		margin:30px;
	}
</style>
<!--부트스트랩 CDN-->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

</head>
<body class="detail">
	<h1>상세보기</h1>
	작성자 : ${ board.writer }<br><br>
	제목 : ${ board.title }<br><br>
	작내용 : ${ board.content }<br><br>
	<hr>
	
	<a href="list"><button type="button" class="btn btn-outline-secondary">목록보기</button></a>
</body>
</html>