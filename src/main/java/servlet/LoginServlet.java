package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login-servlet", urlPatterns = { "/login-servlet" })
public class LoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		UserBean user = new UserBean();
		user.setUserId(userId);
		user.setPassword(password);

		model.dao.UserDAO dao = new model.dao.UserDAO();
		try {
			UserBean Ub = dao.login(userId, password);
			if (Ub == null) {
				RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
				rd.forward(request, response);
			} else {
				session.setAttribute("user", Ub);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
		rd.forward(request, response);
	}
}
