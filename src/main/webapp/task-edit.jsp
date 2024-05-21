<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.entity.TaskBean,model.entity.CategoryBean,model.entity.UserBean,model.entity.StatusBean,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
TaskBean task = new TaskBean();
List<TaskBean> taskList = (List<TaskBean>)session.getAttribute("taskList");
int taskId = (int)(request.getAttribute("taskId"));
for(TaskBean t : taskList){
	if(t.getTaskId() == taskId){
		task = t;
		break;
	}
}
List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
String sd = "selected";
%>
<title>タスク編集画面</title>
</head>
<body>
<form action="task-edit-servlet" method="POST">

<table border=1>
	<tr><th>タスク名</th><td><input type="text" value="<%=task.getTaskName()%>"  name= "taskName" required></td></tr>
	
	<tr><th>カテゴリ</th>
	<td> <select name="categoryId">
	 <%for (CategoryBean cb : categoryList) {%>
		<option value="<%=cb.getCategoryId()%>"><%=cb.getCategoryName()%>
		<%if(){
			
		}%>
		</option>
		<% } %>
		</select></td>
	</tr>
	
	<tr><th>期限</th><td><input type="date" value="<%=task.getLimitDate()%>" name = "limitDate" required></td></tr>
	
	<tr><th>担当者</th>
	<td> <select name="userId">
	 <% for (UserBean ub : userList) { %>
		<option value="<%=ub.getUserId()%>"><%=ub.getUserName()%></option>
		<% } %>
		</select></td>
		</tr>
		
	<tr><th>ステータス</th>
	<td> <select name="statusCode">
	 <% for (StatusBean sb : statusList) { %>
		<option value="<%=sb.getStatusCode()%>"><%=sb.getStatusName()%></option>
		<% } %>
		</select></td>
		</tr>
	
	<tr><th>メモ</th><td><input type="text" value="<%=task.getMemo()%>" name = "memo"></td></tr>
	
	</table>
	
	<input type="hidden" name="taskId" value=<%=task.getTaskId()%>>
	<input type="submit" value="送信">
	</form>
	
	<form action="task-list-servlet" method="POST">
	<input type="submit" value="キャンセル">
	</form>

</body>
</html>