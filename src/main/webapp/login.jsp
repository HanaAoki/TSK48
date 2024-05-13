<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
ログイン画面

<form action="LoginServlet" method="POST"><br>
ユーザID：<input type="text" name = "user_id"><br>
パスワード：<input type="password" name = "password"><br>
<br>
<input type="submit" value="ログイン">
<input type="reset" value="取消">
</form>
<br>
</body>
</html>