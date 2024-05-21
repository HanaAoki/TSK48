<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除結果画面</title>
<%
String resultText = (String)request.getAttribute("resultText");
%>
</head>
<body>
<%@ include file="user-name-header.jsp" %>
<h1><%=resultText%></h1>
<form action="menu.jsp" method="POST">
<input type="submit" value="メニュー画面へ">
</form>
</body>
</html>