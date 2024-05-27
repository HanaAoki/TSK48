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
Integer pageNum = (Integer)request.getAttribute("page");
if(pageNum==null){
	pageNum = 0;
}
%>
<script type="text/javascript">
    const updateButtonStates = () => {
        const radioButtons = document.getElementsByName("taskId");
        const checkboxes = document.querySelectorAll('input[name="taskId[]"]:checked');
        const editButton = document.getElementById("editButton");
        const deleteButton = document.getElementById("deleteButton");

        let radioChecked = false;
        for (let i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].checked) {
                radioChecked = true;
                break;
            }
        }

        editButton.disabled = !radioChecked;
        deleteButton.disabled = checkboxes.length === 0;
    }

    const clearSelections = (event) => {
        event.preventDefault(); // デフォルトのクリア動作をキャンセル
        const radioButtons = document.getElementsByName("taskId");
        const checkboxes = document.getElementsByName("taskId[]");

        for (let i = 0; i < radioButtons.length; i++) {
            radioButtons[i].checked = false;
        }

        for (let i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = false;
        }

        updateButtonStates();
    }

    document.addEventListener('DOMContentLoaded', (event) => {
        updateButtonStates();
        
        const radioButtons = document.getElementsByName("taskId");
        for (let i = 0; i < radioButtons.length; i++) {
            radioButtons[i].addEventListener('change', updateButtonStates);
        }

        const checkboxes = document.getElementsByName("taskId[]");
        for (let i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener('change', updateButtonStates);
        }

        const clearButton = document.getElementById("clearButton");
        clearButton.addEventListener('click', clearSelections);
    });
</script>
</head>
<body>
<%@ include file="user-name-header.jsp" %><br>
	<a href="task-list-servlet?page=<%=pageNum%>&count=-1">&lt;前</a>
	<a href="task-list-servlet?page=<%=pageNum%>&count=1">次&gt;</a><br>
    <form action="get-category-status-servlet" method="GET">
    <table border=1>
    <tr>
        <th>タスク名</th><th>カテゴリ</th><th>期限</th><th>担当者</th><th>ステータス</th><th>メモ</th>
        <th>編集</th>
        <th>削除</th>
        <th>コメント</th>
    </tr>
    <%
        Object limitDate = "";
        for(TaskBean task : taskList){
            if(task.getLimitDate() != null){
                limitDate = task.getLimitDate();
            } else {
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
        <input type="radio" id="radio1" name="taskId" value="<%=task.getTaskId()%>"><%
            }
        %>
        <p id="message"></p>
        </td>
        <td><%
            if(task.getUserName().equals(thisUserName)){
        %>
        <input type="hidden" name="taskId[]" value="0">
        <input type="checkbox" name="taskId[]" value="<%=task.getTaskId()%>"><%
            }
        %>
        </td>
        <td><a href="comment-list-servlet?taskId=<%=task.getTaskId()%>"><%=task.getCommentNum()%></a></td>
    </tr>
    <%
        }
    %>
    </table>
    
    <input type="reset" id="clearButton" value="クリア">
    <input type="submit" id="editButton" value="編集" disabled>
    <input type="submit" id="deleteButton" value="削除" formaction="task-delete-confirm.jsp" disabled>
    </form>
    <br>
    <form action="menu.jsp" method="POST">
    <input type="submit" value="メニュー画面へ">
    </form>
</body>
</html>