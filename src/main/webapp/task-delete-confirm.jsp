<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean, java.util.List, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<%
List<TaskBean> tasks = new ArrayList<TaskBean>();
List<TaskBean> taskList = (List<TaskBean>)session.getAttribute("taskList");
String[] taskId = request.getParameterValues("taskId[]");
session.setAttribute("taskId", taskId);
for(TaskBean t : taskList){
	for(int i = 0; i < taskId.length; i++){
		if(t.getTaskId() == Integer.parseInt(taskId[i])){
			tasks.add(t);
			break;
		}
	}
}
%>
</head>
<body>
	<form action="task-delete-servlet" method="POST">
	<%
	for(TaskBean task : tasks){
	%>
	<table border=1>
	<tr><th>タスク名</th><td><%=task.getTaskName()%></td></tr>
	<tr><th>カテゴリ</th><td><%=task.getCategoryName()%></td></tr>
	<tr><th>期限</th><td><%=task.getLimitDate()%></td></tr>
	<tr><th>担当者</th><td><%=task.getUserName()%></td></tr>
	<tr><th>ステータス</th><td><%=task.getStatusName()%></td></tr>
	<tr><th>メモ</th><td><%=task.getMemo()%></td></tr>
	</table><%=task.getTaskId()%>
	<br>
	<%
	}
	if(tasks.size() != 0){
	%>
	<input type="submit" value="削除">
	<%
	}
	%>
	</form>
	
	<form action="task-list-servlet" method="POST">
	<input type="submit" value="キャンセル">
	</form>
</body>
</html>