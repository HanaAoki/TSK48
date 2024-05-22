<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.entity.TaskBean,model.entity.CategoryBean,model.entity.UserBean,model.entity.StatusBean,java.util.ArrayList,java.util.List, java.time.LocalDate"%>
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
session.setAttribute("task", task);
List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
String sd = "";
LocalDate today = LocalDate.now();
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
	 <%if(cb.getCategoryId() == task.getCategoryId()){
		 sd = "selected";
		}else{
		sd="selected";
		}%>
		<option value="<%=cb.getCategoryId()%>"<%=sd%>><%=cb.getCategoryName()%>
		
		</option>
		<% } %>
		</select></td>
	</tr>
	
	<tr><th>期限</th><td><input type="date" min="<%=today %>" value="<%=task.getLimitDate()%>" name = "limitDate"></td></tr>
	
	<tr><th>担当者</th>
	<td> <select name="userId">
	 <% for (UserBean ub : userList) { %>
	  <%if(ub.getUserId().equals(task.getUserId())){
		 sd = "selected";
		}else{
		sd="";
		}%>
		<option value="<%=ub.getUserId()%>"<%=sd%>><%=ub.getUserName()%></option>
		<% } %>
		</select></td>
		</tr>
		
	<tr><th>ステータス</th>
	<td> <select name="statusCode">
	 <% for (StatusBean sb : statusList) { %>
	  <%if(sb.getStatusCode().equals(task.getStatusCode())){
		 sd = "selected";
		}else{
		sd="";
		}%>
		<option value="<%=sb.getStatusCode()%>"<%=sd%>><%=sb.getStatusName()%></option>
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