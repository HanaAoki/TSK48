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






</form>
</body>
</html>