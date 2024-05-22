<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント登録結果確認画面</title>
</head>
<body>
<%
CommentBean cb = (CommentBean)request.getAttribute("cb");
String resultText = (String)request.getAttribute("resultText");
%>
</head>
<body>
<h1><%=resultText%></h1>
<%@ include file="user-name-header.jsp" %>
<p>

	
	
<form action="task-list-servlet" method="POST">
<input type="submit" value="タスク一覧画面へ">
</form>
</body>
</html>