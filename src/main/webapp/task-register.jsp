<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, java.util.List, java.time.LocalDate, model.entity.CategoryBean, model.entity.StatusBean, model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
<h1>タスク登録画面</h1>

<%@ include file="user-name-header.jsp" %>

<p>

	<%
	List<CategoryBean> categoryList = (List) session.getAttribute("categoryList");
	List<StatusBean> statusList = (List) session.getAttribute("statusList");
	List<UserBean> userList = (List) session.getAttribute("userList");
	LocalDate today = LocalDate.now();
	LocalDate tomorrow = today.plusDays(1);
	%>

	<table border="1">
		<form action="task-add-servlet" method="post">
			<tr>
				<th>
					タスク名
				</th>
				<td>
					<input type="text" name="taskName" size="50" max="50" required>
				</td>
			</tr>
			<tr>
				<th>
					カテゴリ
				</th>
				<td>
					<select name="categoryId">
					<%
						for (CategoryBean categoryBean : categoryList) { 
					%>
						<option value="<%=categoryBean.getCategoryId() %>"><%=categoryBean.getCategoryName() %></option>
					<%
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					期限
				</th>
				<td>
					<input type="date" name="taskLimit" min="<%=today %>" value="<%=tomorrow %>">
				</td>
			</tr>
			<tr>
				<th>
					担当者
				</th>
				<td>
					<select name="userId">
					<%
						for (UserBean userBean : userList) { 
					%>
						<option value="<%=userBean.getUserId() %>"><%=userBean.getUserName() %></option>
					<%
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					ステータス 
				</th>
				<td>
					<select name="statusCode">
					<%
						for (StatusBean statusBean : statusList) { 
					%>
						<option value="<%=statusBean.getStatusCode() %>"><%=statusBean.getStatusName() %></option>
					<%
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					メモ
				</th>
				<td>
					<textarea name="memo"  max="100" cols="30" rows="4"></textarea>
				</td>
			</tr>
		</table>
		<br>
		
		<table>
			<tr>
				<td>
					<input type="submit" value="登録">
				</td>
				<td>
					<input type="reset" value="クリア">
				</td>
	</form>
				<td>
					<form action="menu.jsp" method="get">
						<input type="submit" value="戻る">
					</form>
				</td>
			</tr>
	</table>

</body>
</html>