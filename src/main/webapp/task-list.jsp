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
	<form action="get-category-status-servlet" method="GET">
	<table border=1>
	<tr>
		<th>タスク名</th><th>カテゴリ</th><th>期限</th><th>担当者</th><th>ステータス</th><th>メモ</th>
		<th><input type="submit" value="編集"></th>
		<th><input type="submit" value="削除" formaction="task-delete-confirm.jsp"></th>
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
		<td><input type="radio" name="taskId" value="<%=task.getTaskId()%>"></td>
		<td>
		<input type="hidden" name="taskId[]" value="0">
		<input type="checkbox" name="taskId[]" value="<%=task.getTaskId()%>">
		</td>
	</tr>
	<%
		}
	%>
	</table>
	<input type="reset" value="クリア">
	</form>
	<br>
	<form action="menu.jsp" method="POST">
	<input type="submit" value="メニュー画面へ">
	</form>
</body>
</html>