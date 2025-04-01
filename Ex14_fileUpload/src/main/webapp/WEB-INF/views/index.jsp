<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
<script src="/webjars/bootstrap/5.3.3/js/bootstrap.min.js"></script>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<form action="uploadOK" method="post" enctype="multipart/form-data">
		파일 : <input type="file" name="files" multiple><br><br>
		<input type="file" name="files" multiple><br><br>
		<input type="submit" value="File Upload"><br>
	</form>
	
</body>
</html>