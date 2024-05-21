<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集完了画面</title>
<%
TaskBean task = (TaskBean)request.getAttribute("task");
String resultText = (String)request.getAttribute("resultText");
%>
</head>
<body>
<h1><%=resultText%></h1>

	<table border=1>
	<tr><th>タスク名</th><td><%=task.getTaskName()%></td></tr>
	<tr><th>カテゴリ</th><td><%=task.getCategoryName()%></td></tr>
	<tr><th>期限</th><td><%=task.getLimitDate()%></td></tr>
	<tr><th>担当者</th><td><%=task.getUserName()%></td></tr>
	<tr><th>ステータス</th><td><%=task.getStatusName()%></td></tr>
	<tr><th>メモ</th><td><%=task.getMemo()%></td></tr>
	</table>
	
<form action="menu.jsp" method="POST">
<input type="submit" value="メニュー画面へ">
</form>
</body>
</html>