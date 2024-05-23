package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentListDAO;
import model.entity.CommentBean;

/**
 * 
 * @author Arino
 */
@WebServlet("/comment-list-servlet")
public class CommentListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("comment-list.jsp");
		HttpSession session = request.getSession();
		CommentListDAO cmtListDAO = new CommentListDAO();
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		try {
			commentList = cmtListDAO.selectCommentList(taskId);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("taskId", taskId);
		session.setAttribute("commentList", commentList);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("comment-list.jsp");
		HttpSession session = request.getSession();
		CommentListDAO cmtListDAO = new CommentListDAO();
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		int taskId = (Integer)session.getAttribute("taskId");
		session.removeAttribute("taskId");	
		
		try {
			commentList = cmtListDAO.selectCommentList(taskId);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("taskId", taskId);
		session.setAttribute("commentList", commentList);
		
		rd.forward(request, response);
	}
}
