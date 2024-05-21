package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskAddDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;


/**
 * Servlet implementation class TaskAddServlet
 */
/**
 * タスクを登録するためのサーブレット
 * @author 青木春菜
 */
@WebServlet("/task-add-servlet")
public class TaskAddServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		セッションの取得
		HttpSession session = request.getSession();
		List<CategoryBean> categoryList = (List) session.getAttribute("categoryList");
		List<StatusBean> statusList = (List) session.getAttribute("statusList");
		List<UserBean> userList = (List) session.getAttribute("userList");
//		入力されたものの取得
		String taskName = request.getParameter("taskName");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		Date limitDate = Date.valueOf(request.getParameter("taskLimit"));
		String userId = request.getParameter("userId");
		String statusCode = request.getParameter("statusCode");
		String memo = request.getParameter("memo");
		
//		プルダウンで選ばれたものの名前を取得
		String categoryName = "";
		for(CategoryBean category : categoryList) {
			if(category.getCategoryId() == categoryId) {
				categoryName = category.getCategoryName();
			}
		}
		String statusName = "";
		for(StatusBean status : statusList) {
			if(status.getStatusCode().equals(statusCode)) {
				statusName = status.getStatusName();
			}
		}
		String userName = "";
		for(UserBean user : userList) {
			if(user.getUserId().equals(userId)) {
				userName = user.getUserName();
			}
		}
		
//		taskbeanに入れる
		TaskBean taskBean = new TaskBean();
		taskBean.setTaskName(taskName);
		taskBean.setCategoryId(categoryId);
		taskBean.setCategoryName(categoryName);
		taskBean.setLimitDate(limitDate);
		taskBean.setUserId(userId);
		taskBean.setUserName(userName);
		taskBean.setStatusCode(statusCode);
		taskBean.setStatusName(statusName);
		taskBean.setMemo(memo);
		
//		文言の分岐
		TaskAddDAO taskAddDAO = new TaskAddDAO();
		String resultText = "";
		try {
		int count = taskAddDAO.addTask(taskBean);
		
		if(count == 0) {
			resultText = "以下のタスク登録に失敗しました。";
		}
		if(count >0) {
			resultText = count + "以下のタスクを登録しました。";
		}
		
		request.setAttribute("task", taskBean);
		request.setAttribute("resultText", resultText);
		
		RequestDispatcher rd = request.getRequestDispatcher("task-register-result.jsp");
		rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
