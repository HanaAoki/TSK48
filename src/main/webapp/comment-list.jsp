<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.CommentBean, java.util.List, model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント一覧</title>
<%
List<CommentBean> commentList =(List<CommentBean>) session.getAttribute("commentList");
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
<script type="text/javascript">
    const updateButtonStates = () => {
        const checkboxes = document.querySelectorAll('input[name="index"]:checked');
        const deleteButton = document.getElementById("deleteButton");
        deleteButton.disabled = checkboxes.length === 0;
    }

    const handleClear = () => {
        setTimeout(updateButtonStates, 0);
    }

    document.addEventListener('DOMContentLoaded', (event) => {
        updateButtonStates();
        
        const checkboxes = document.getElementsByName("index");
        for (let i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener('change', updateButtonStates);
        }

        const clearButton = document.getElementById("clearButton");
        clearButton.addEventListener('click', handleClear);
    });
</script>
</head>
<body>
	<%@ include file="user-name-header.jsp" %>
	<form action="comment-delete-confirm.jsp" method="POST">
	<table border=1>
	<tr>
		<th>タスク名</th><th>担当者</th><th>コメント</th><th>削除</th>
	</tr>
	<%
	for(CommentBean comment : commentList){
		%>
	<tr>
		<td><%=comment.getTaskName()%></td>
		<td><%=comment.getUserName()%></td>
		<td><%=comment.getComment()%></td>
		<td>
		<%
		if(comment.getUserName().equals(thisUserName)){
			%>
			<input type="checkbox" name="index" value="<%=index%>">
			<%
		}%>
		</td>
	</tr>
		<%
		index++;
	}
	%>
	</table>
	<input type="reset" id="clearButton" value="クリア">
	<input type="submit" id="deleteButton" value="削除" disabled>
	</form>
	
	<%
	if(thisUserId != null){
	%>
	<form action="comment-add-servlet" method="POST">
		<input type="hidden" name="taskId" value="<%=taskId%>">
		<input type="hidden" name="userId" value="<%=thisUserId%>"><br>
		
		<label for="comment">コメント投稿</label><br>
		<textarea id="comment" name="comment" rows="4" cols="40"></textarea><br>

		<input type="submit" value="投稿">
	</form>
	<%
	}
	%>
	
	<form action="task-list-servlet" method="POST">
	<input type="submit" value="戻る">
	</form>
</body>
</html>