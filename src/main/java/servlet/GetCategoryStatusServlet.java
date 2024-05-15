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

import model.dao.CategoryDAO;
import model.dao.StatusDAO;
import model.dao.UserListDAO;


/**
 * Servlet implementation class GetCategoryStatusServlet
 */
/**
 * categoryとstatusのプルダウンを表示するためのセッションを取得するためのサーブレット
 * @author 青木春菜
 */
@WebServlet("/get-category-status-servlet")
public class GetCategoryStatusServlet extends HttpServlet {

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * タスク一覧表示画面からタスク編集画面へのサーブレット
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//		categorydaoのインスタンス化
		CategoryDAO categoryDAO = new CategoryDAO();
		
//		Statusdaoのインスタンス化
		StatusDAO statusDAO = new StatusDAO();
		
//		categorydaoのインスタンス化
		UserListDAO userListDAO = new UserListDAO();
		
		try {
//			categorybeanとstatusbeanのlistをセッションに入れる
			HttpSession session = request.getSession();
			session.setAttribute("categoryList", categoryDAO.selectCategory());
			session.setAttribute("statusList", statusDAO.selectStatus());
			session.setAttribute("userList", userListDAO.userList());
			
//			task-edit.jspに遷移させる
			RequestDispatcher rd = request.getRequestDispatcher("task-edit.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * メニュー画面からタスク登録画面へのサーブレット
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		categorydaoのインスタンス化
		CategoryDAO categoryDAO = new CategoryDAO();
		
//		Statusdaoのインスタンス化
		StatusDAO statusDAO = new StatusDAO();
		
//		categorydaoのインスタンス化
		UserListDAO userListDAO = new UserListDAO();
		
		try {
//			categorybeanとstatusbeanのlistをセッションに入れる
			HttpSession session = request.getSession();
			session.setAttribute("categoryList", categoryDAO.selectCategory());
			session.setAttribute("statusList", statusDAO.selectStatus());
			session.setAttribute("userList", userListDAO.userList());
			
//			task-register.jspに遷移させる
			RequestDispatcher rd = request.getRequestDispatcher("task-register.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
