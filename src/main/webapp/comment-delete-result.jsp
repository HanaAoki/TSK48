<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除完了</title>
</head>
<body>
<%
String resultText = (String)request.getAttribute("resultText");
%>
</head>
<body>
<%@ include file="user-name-header.jsp" %>
<h1><%=resultText%></h1>
<form action="task-list-servlet" method="POST">
<input type="submit" value="タスク一覧画面へ">
</form>
</body>
</html>