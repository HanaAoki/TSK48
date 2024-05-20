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

import model.dao.TaskEditDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskEditServlet
 */
/**
 * タスク編集を行うサーブレットです
 * @author Arino
 */
@WebServlet("/task-edit-servlet")
public class TaskEditServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("task-edit-result.jsp");//編集完了画面のPath
		HttpSession session = request.getSession();
		
		TaskEditDAO taskEditDAO = new TaskEditDAO();
		int count = 0;
		String resultText = "";
		
		//userListは仮、まだ無い。
		List<CategoryBean> categoryList = (List<CategoryBean>)session.getAttribute("categoryList");
		List<StatusBean> statusList = (List<StatusBean>)session.getAttribute("statusList");
		List<UserBean> userList = (List<UserBean>)session.getAttribute("userList");
		TaskBean task = new TaskBean();
		
		//いらないのが含まれているので後で消す。
		int taskId = Integer.parseInt(request.getParameter("taskId"));//取得方法未定
		String taskName = request.getParameter("taskName");
		int categoryId = 0;
		String categoryName = request.getParameter("categoryName");
		Date limitDate = Date.valueOf(request.getParameter("limitDate"));
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String statusCode = "";
		String statusName = request.getParameter("statusName");
		String memo = request.getParameter("memo");
		
		//categoryId, statusCode, userIdの取得
		for(CategoryBean category : categoryList) {
			if(category.getCategoryName().equals(categoryName)) {
				categoryId = category.getCategoryId();
			}
		}
		for(StatusBean status : statusList) {
			if(status.getStatusName().equals(statusName)) {
				statusCode = status.getStatusCode();
			}
		}
		for(UserBean user : userList) {
			if(user.getUserName().equals(userName)) {
				userId = user.getUserId();
			}
		}
		
		//とりあえず全部入れてる。
		task.setTaskId(taskId);
		task.setTaskName(taskName);
		task.setCategoryId(categoryId);
		task.setCategoryName(categoryName);
		task.setLimitDate(limitDate);
		task.setUserId(userId);
		task.setUserName(userName);
		task.setStatusCode(statusCode);
		task.setStatusName(statusName);
		task.setMemo(memo);
		
		try {
			count = taskEditDAO.editTask(task);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(count == 0) {
			resultText = "以下のタスク編集に失敗しました。";
		}
		if(count >0) {
			resultText = count + "以下のタスクを編集しました。";
		}
		
		request.setAttribute("task", task);
		request.setAttribute("resultText", resultText);
		
		rd.forward(request, response);
	}

}