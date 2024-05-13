<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.ArrayList, model.entity.TaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧画面</title>
<%
List<TaskBean> taskList = (List<TaskBean>)request.getAttribute("taskList");
%>
</head>
<body>
	<table border=1>
	<tr>
		<th>タスク名</th><th>カテゴリ</th><th>期限</th><th>担当者</th><th>ステータス</th><th>メモ</th>
	</tr>
	<%
		for(TaskBean task : taskList){
	%>
	<tr>
		<td><%=task.getTaskName()%></td>
		<td><%=task.getCategoryName()%></td>
		<td><%=task.getLimitDate()%></td>
		<td><%=task.getUserName()%></td>
		<td><%=task.getStatusName()%></td>
		<td><%=task.getMemo()%></td>
	</tr>
	<%
		}
	%>
	</table>
</body>
</html>