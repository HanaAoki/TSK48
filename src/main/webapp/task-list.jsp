<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.ArrayList, model.entity.TaskBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧画面</title>
<%
List<TaskBean> taskList = (List<TaskBean>)session.getAttribute("taskList");
UserBean thisUser = (UserBean)session.getAttribute("user");
String thisUserName = "ゲスト";
String thisUserId = null;
if(thisUser != null){
	thisUserName = thisUser.getUserName();
	thisUserId = thisUser.getUserId();
}
int taskId = (Integer)request.getAttribute("taskId");
int index = 0;
%>
</head>
<body>
<%@ include file="user-name-header.jsp" %>
<p>
	<form action="get-category-status-servlet" method="GET">
	<table border=1>
	<tr>
		<th>タスク名</th><th>カテゴリ</th><th>期限</th><th>担当者</th><th>ステータス</th><th>メモ</th>
		<th><input type="submit" value="編集"></th>
		<th><input type="submit" value="削除" formaction="task-delete-confirm.jsp"></th>
		<th>コメント</th>
	</tr>
	<%
		Object limitDate = "";
		for(TaskBean task : taskList){
			if(task.getLimitDate() != null){
				limitDate = task.getLimitDate();
			}else{
				limitDate = "";
			}
	%>
	<tr>
		<td><%=task.getTaskName()%></td>
		<td><%=task.getCategoryName()%></td>
		<td><%=limitDate%></td>
		<td><%=task.getUserName()%></td>
		<td><%=task.getStatusName()%></td>
		<td><%=task.getMemo()%></td>
		<td><%
		if(task.getUserName().equals(thisUserName)){
			%>
		<input type="radio" name="taskId" value="<%=task.getTaskId()%>"><%
		}%></td>
		<td><%
		if(task.getUserName().equals(thisUserName)){
			%>
		<input type="hidden" name="taskId[]" value="0">
		<input type="checkbox" name="taskId[]" value="<%=task.getTaskId()%>"><%
		}%>
		</td>
		<td><a href="comment-list-servlet?taskId=<%=task.getTaskId()%>"><%=task.getCommentNum()%></a></td>
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