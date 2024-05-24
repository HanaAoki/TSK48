<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.UserBean, model.entity.TaskBean, model.entity.ScheduleBean, java.time.LocalDate, java.text.SimpleDateFormat, java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
List<UserBean> userList = (List<UserBean>)request.getAttribute("userList");
List<ScheduleBean> scheduleList = (List<ScheduleBean>)request.getAttribute("scheduleList");
LocalDate date = (LocalDate)request.getAttribute("date");
SimpleDateFormat yearMonth = new SimpleDateFormat("yyyy年M月");
SimpleDateFormat nowDay = new SimpleDateFormat("dd");
int day = Integer.parseInt(nowDay.format(Date.valueOf(date)));
%>
</head>
<body>
	<table border = 1>
		<tr>
			<td rowspan="2">従業員</td>
			<td colspan="7"><%=yearMonth.format(Date.valueOf(date))%></td>
		</tr>
		<tr>
			<%
			for(int i = 0; i < 7; i++){
				%><td><%=day + i%></td><%
			}
			%>
			
		</tr>
		<%
		for(ScheduleBean schedule : scheduleList){
		%>
		<tr>
			<td><%=schedule.getUserName()%></td>
			<%
			for(int i = 0; i < schedule.getRoom().length; i++){
				%>
				<td><%=schedule.getRoom()[i]%></td>
				<%
			}
			%>
		</tr>
		<%
		}
		%>
			
	</table>
	
	<form action="menu.jsp" method="POST">
	<input type="submit" value="メニュー画面へ">
	</form>
	
</body>
</html>