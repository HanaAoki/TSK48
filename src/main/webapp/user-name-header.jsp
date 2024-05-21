<%@ page  pageEncoding="UTF-8" import="model.entity.UserBean"%>

<%
	UserBean user = (UserBean) session.getAttribute("user");
	String userName = user.getUserName();
%>

<%=userName %>が使用中