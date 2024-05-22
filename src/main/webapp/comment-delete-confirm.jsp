<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.stream.Stream, model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除確認</title>
</head>
<body>
	<%
		List<CommentBean> commentList = (List) session.getAttribute("commentList");
		String[] indexS = request.getParameterValues("index");
		int[] index = Stream.of(indexS).mapToInt(Integer::parseInt).toArray();
		session.setAttribute("index", index);
	%>
		
		<table border="1">
			<tr>
				<th>
					ユーザー名
				</th>
				<th>
					コメント
				</th>
			</tr>
			<%
				for (int i = 0; i < index.length; i++) {
				CommentBean commentBean = commentList.get(index[i]);
				String userName = commentBean.getUserName();
				String comment = commentBean.getComment();
			%>
			<tr>
				<td>
					<%=comment %>
				</td>
				<td>
					<%=userName %>
				</td>
			</tr>
			<%
				}
				%>
		</table>
	<%
		
	%>
	
	<form action="comment-delete-servlet" method="post">
		<input type="submit" value="削除">
	</form>
</body>
</html>