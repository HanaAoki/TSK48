<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.TaskBean, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<%
TaskBean task = new TaskBean();
List<TaskBean> taskList = (List<TaskBean>)session.getAttribute("taskList");
int taskId = Integer.parseInt(request.getParameter("taskId"));
for(TaskBean t : taskList){
	if(t.getTaskId() == taskId){
		task = t;
		break;
	}
}
%>
</head>
<body>
	<table border=1>
	
	<tr><th>タスク名</th><td><%=task.getTaskName()%></td></tr>
	<tr><th>カテゴリ</th><td><%=task.getCategoryName()%></td></tr>
	<tr><th>期限</th><td><%=task.getLimitDate()%></td></tr>
	<tr><th>担当者</th><td><%=task.getUserName()%></td></tr>
	<tr><th>ステータス</th><td><%=task.getStatusName()%></td></tr>
	<tr><th>メモ</th><td><%=task.getMemo()%></td></tr>
	
	</table>
	<form action="task-delete-servlet" method="POST">
	<input type="hidden" name="taskId" value=<%=task.getTaskId()%>>
	<input type="submit" value="削除">
	<input type="submit" value="キャンセル" formaction="task-list-servlet">
	</form>
</body>
</html>