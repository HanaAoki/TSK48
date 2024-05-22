package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentAddDAO;
import model.entity.CommentBean;

/**
 * Servlet implementation class CommentAddServlet
 */
/**
 * コメントを登録するためのサーブレット
 * @author 重松
 */
@WebServlet("/comment-add-servlet")
public class CommentAddServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		int taskId = Integer.parseInt(request.getParameter("taskId"));
		String userId = request.getParameter("userId");
		String comment = request.getParameter("comment");

		CommentBean cb = new CommentBean();
		
		cb.setTaskId(taskId);
		cb.setUserId(userId);
		cb.setComment(comment);
		
		CommentAddDAO commentAddDAO = new CommentAddDAO();
		String resultText = "";
		try {
			int count = commentAddDAO.addTask(cb);

			if (count == 0) {
				resultText = "以下のコメント登録に失敗しました。";
			}
			if (count != 0) {
				resultText = "以下のコメントを登録しました。";
			}
			request.setAttribute("cb", cb);
			request.setAttribute("resultText", resultText);

			RequestDispatcher rd = request.getRequestDispatcher("comment-register-result.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		
		}
}