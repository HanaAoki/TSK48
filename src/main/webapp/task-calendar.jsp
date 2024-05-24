<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.UserBean, model.entity.TaskBean, model.entity.ScheduleBean, java.time.LocalDate, java.time.format.DateTimeFormatter, java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
List<UserBean> userList = (List<UserBean>)request.getAttribute("userList");
List<ScheduleBean> scheduleList = (List<ScheduleBean>)request.getAttribute("scheduleList");
LocalDate date = (LocalDate)request.getAttribute("date");
String dateStr = date.toString();
DateTimeFormatter yearMonthDay = DateTimeFormatter.ofPattern("yyyy年M月d日(E)");
DateTimeFormatter yearMonth = DateTimeFormatter.ofPattern("yyyy年M月");
DateTimeFormatter month = DateTimeFormatter.ofPattern("M月");
DateTimeFormatter nowDay = DateTimeFormatter.ofPattern("dd");
DateTimeFormatter nowDayOfWeek = DateTimeFormatter.ofPattern("d(E)");
String[] dayList = new String[7];
int monthChangePoint = 0;
for(int i = 0; i < 7; i++){
	int day = Integer.parseInt(date.format(nowDay));
	String dayOfWeek = date.format(nowDayOfWeek);
	if(i != 0 && day == 1){
		monthChangePoint = i;
	}
	dayList[i] = dayOfWeek;
	date = date.plusDays(1);
}
date = date.minusDays(7);
String[] mon = new String[2];
if(monthChangePoint > 3 || monthChangePoint == 0){
	mon[0] = date.format(yearMonth);
	date = date.plusMonths(1);
	mon[1] = date.format(month);
}else{
	mon[0] = date.format(month);
	date = date.plusMonths(1);
	mon[1] = date.format(yearMonth);
}
date = date.minusMonths(1);
%>
</head>
<body>
	<%@ include file="user-name-header.jsp" %><br>
	<a href="task-calendar-servlet?dayShift=-1&date=<%=dateStr%>">&lt;</a>
	<label><%=date.format(yearMonthDay)%></label>
	<a href="task-calendar-servlet?dayShift=1&date=<%=dateStr%>">&gt;</a>
	<table border = 1>
		<tr>
			<td rowspan="2">従業員</td>
			<%
			if(monthChangePoint > 0){
				%>
				<td colspan="<%=monthChangePoint%>"><%=mon[0]%></td>
				<td colspan="<%=7 - monthChangePoint%>"><%=mon[1]%></td>
				<%
			}else{
			%>
				<td colspan="7"><%=mon[0]%></td>
			<%}%>
		</tr>
		<tr>
			<%
			
			for(int i = 0; i < 7; i++){
				%><td><%=dayList[i]%></td><%
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