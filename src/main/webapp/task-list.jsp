<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.ArrayList, model.entity.TaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧画面</title>
<%
List<TaskBean> taskList = (List<TaskBean>)session.getAttribute("taskList");
%>
</head>
<body>
	<table border=1>
	<tr>
		<th>タスク名</th><th>カテゴリ</th><th>期限</th><th>担当者</th><th>ステータス</th><th>メモ</th><th>編集</th><th>削除</th>
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
		<td><a href="/get-category-status-servlet?taskId=<%=task.getTaskId()%>">編集</a></td>
		<td><a href="task-delete-confirm.jsp?taskId=<%=task.getTaskId()%>">削除</a></td>
	</tr>
	<%
		}
	%>
	</table>
</body>
</html>