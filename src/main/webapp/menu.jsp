<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>

<%
UserBean userBean = (UserBean) session.getAttribute("user");
if (userBean != null) {
	if ((Long) request.getAttribute("limit") != null) {
		Long limit = (Long) request.getAttribute("limit");
		if (limit <= 3 && limit >= 1) {
%>
	<script>
	alert('期限は残り' + <%=limit %> + '日です。');
	</script>
<%
		}
		if (limit == 0) {
			%>
			<script>
			alert('期限は本日です。');
			</script>
		<%
		}
	}
}
%>

<%@ include file="user-name-header.jsp" %>
<form action="get-category-status-servlet" method="POST">
<input type="submit" value="タスク登録">
<input type="submit" value="タスク一覧" formaction="task-list-servlet">
<input type="submit" value="カレンダー" formaction="task-calendar-servlet">
<input type="submit" value="ログアウト" formaction="logout.jsp">
</form>
</body>
</html>