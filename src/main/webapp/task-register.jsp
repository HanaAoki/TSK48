<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, java.util.List, model.entity.CategoryBean, model.entity.StatusBean, model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<%
	List<CategoryBean> categoryList = (List) session.getAttribute("categoryList");
	List<StatusBean> statusList = (List) session.getAttribute("statusList");
	List<UserBean> userList = (List) session.getAttribute("userList");
	%>

	<form action="task-add-servlet" method="post">
		タスク名
		<br>
		<input type="text" name="taskName" size="50" max="50">
		<br>
		カテゴリ
		<br>
		<select name="categoryId">
		<%
		for (CategoryBean categoryBean : categoryList) { 
		%>
			<option value="<%=categoryBean.getCategoryId() %>"><%=categoryBean.getCategoryName() %></option>
		<%
		}
		%>
		</select>
		<br>
		期限
		<br>
			<input type="date" name="taskLimit">
		<br>
		ユーザー
		<br>
		<select name="userId">
		<%
		for (UserBean userBean : userList) { 
		%>
			<option value="<%=userBean.getUserId() %>"><%=userBean.getUserName() %></option>
		<%
		}
		%>
		</select>
		<br>
		ステータス 
		<br>
		<select name="statusCode">
		<%
		for (StatusBean statusBean : statusList) { 
		%>
			<option value="<%=statusBean.getStatusCode() %>"><%=statusBean.getStatusName() %></option>
		<%
		}
		%>
		</select>
		<br>
		メモ
		<br>
		<input type="text" name="memo" size="100" max="100">
		
		<br>
		
		<input type="submit" value="登録">
	</form>

</body>
</html>