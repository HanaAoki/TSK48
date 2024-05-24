package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SelectLimitDateDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
/**
 * ログインの判定を行うサーブレットです
 * @author 重松
 */
@WebServlet(name = "login-servlet", urlPatterns = { "/login-servlet" })
public class LoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//セッションの開始
		HttpSession session = request.getSession();
		//前画面で入力された内容を変数に代入
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		//UserBeanのインスタンス化
		UserBean user = new UserBean();
		user.setUserId(userId);
		user.setPassword(password);
		//UserDAOのインスタンス化
		model.dao.UserDAO dao = new model.dao.UserDAO();
		try {
			//loginメソッドの実行
			UserBean Ub = dao.login(userId, password);
			//戻り値の内容がないなら失敗画面に遷移
			if (Ub == null) {
				RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
				rd.forward(request, response);
			} else {
				//実行出来たらuserの名前で中身をセッションに登録
				session.setAttribute("user", Ub);
			}
			
			// 期限までの日数を出してjspに投げる
			SelectLimitDateDAO selectLimitDateDAO = new SelectLimitDateDAO();
			LocalDate today = LocalDate.now();
			Date limitDate = selectLimitDateDAO.selectLimitDate(userId);
			LocalDate limit = limitDate.toLocalDate();
			long toLimitDate = ChronoUnit.DAYS.between(today, limit);
			session.setAttribute("limit", toLimitDate);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		//menu.jspに遷移させる
		RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
		rd.forward(request, response);
	}
}
