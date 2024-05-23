<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.UserBean, model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
List<UserBean> userList = (List<UserBean>)session.getAttribute("userList");
List<TaskBean> taskList = (List<TaskBean>)session.getAttribute("taskList");
%>
</head>
<body>
	<table border = 1>
		<tr>
			<td rowspan="2">従業員</td>
			<td colspan="7">2023年8月</td>
		</tr>
		<tr>
			<td>3</td>
			<td>4</td>
			<td>5</td>
			<td>6</td>
			<td>7</td>
			<td>8</td>
			<td>9</td>
		</tr>
		<%
		for(int i=0; i<11; i++){
		%>
		<tr>
			<td>従業員A</td>
			<td>×</td>
			<td>×</td>
			<td>×</td>
			<td>×</td>
			<td>×</td>
			<td>×</td>
			<td>×</td>
		</tr>
		<%
		}
		%>
			
	</table>

</body>
</html>