<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
</head>
<body>
ログアウトしました。

<form action="login.jsp" method="POST"><br>
<input type="submit" value="ログイン画面へ">
</form>

<%
	session.invalidate();
	%>
</body>
</html>