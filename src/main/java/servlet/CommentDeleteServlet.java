package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentDeleteDAO;
import model.entity.CommentBean;

/**
 * Servlet implementation class CommentDeleteServlet
 */
/**
 * コメント削除のためのサーブレットです。
 * @author 青木春菜
 */
@WebServlet("/comment-delete-servlet")
public class CommentDeleteServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
//		準備
		RequestDispatcher rd = request.getRequestDispatcher("comment-delete-result.jsp");
		CommentDeleteDAO commentDeleteDAO = new CommentDeleteDAO();
		int count = 0;
		String resultText = "";
		
//		コメントIDとってくる
		List<CommentBean> commentList = (List) session.getAttribute("commentList");
		int[] index = (int[])session.getAttribute("index");
		int[] commentId = new int[index.length];
		for (int i = 0; i < index.length; i++) {
			CommentBean commentBean = commentList.get(i);
			commentId[i] = commentBean.getCommentId();
		}
		
		
//		タスク削除を実行
		try {
			count = commentDeleteDAO.deleteComment(commentId);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(count == 0) {
			resultText = "指定コメントを削除出来ませんでした。";
		}else {
			resultText = count + "件のコメントを削除しました。";
		}
		
		request.setAttribute("resultText", resultText);
		session.removeAttribute("index");
		rd.forward(request, response);
	

	}

}
