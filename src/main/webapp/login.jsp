<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>ログイン画面</h1>

<form action="login-servlet" method="POST"><br>
ユーザID：<input type="text" name = "user_id" maxlength="20" pattern="^[a-zA-Z0-9]+$" required>（20文字以内）<br>
パスワード：<input type="password" name = "password" maxlength="32" pattern="^[a-zA-Z0-9]+$" required> (32文字以内)<br>
どちらも半角英数字で入力してください。
<br>
<br>
<input type="submit" value="ログイン">
<input type="reset" value="取消">
</form>
<br>
</body>
</html>