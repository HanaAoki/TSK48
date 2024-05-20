<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.entity.TaskBean,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>タスク編集画面</title>
</head>
<body>
<form action="tsk-edit-servlet" method="POST">

<table border=1>
	<tr><th>タスク名</th><td><input type="text" name = "taskName"></td></tr>
	<tr><th>カテゴリ</th><td><%=task.getCategoryName()%></td></tr>
	<tr><th>期限</th><td><input type="date" name = "limitDate"></td></tr>
	<tr><th>担当者</th><td><%=task.getUserName()%></td></tr>
	<tr><th>ステータス</th><td><%=task.getStatusName()%></td></tr>
	<tr><th>メモ</th><td><input type="text" name = "memo"></td></tr>
	</table>
	
	<input type="hidden" name="taskId" value=<%=task.getTaskId()%>>
	<input type="submit" value="送信">
	</form>
	
	<form action="task-list-servlet" method="POST">
	<input type="submit" value="キャンセル">
	</form>

</body>
</html>