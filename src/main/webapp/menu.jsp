<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
<form action="task-register.jsp" method="POST">
<input type="submit" value="タスク登録">
<input type="submit" value="タスク一覧" formaction="task-list-servlet">
<input type="submit" value="ログアウト" formaction="logout.jsp">
</form>
</body>
</html>