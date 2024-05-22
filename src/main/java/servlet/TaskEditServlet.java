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
		RequestDispatcher rd = request.getRequestDispatcher("task-edit-result.jsp");
		HttpSession session = request.getSession();
		
		TaskEditDAO taskEditDAO = new TaskEditDAO();
		TaskBean oldTask = (TaskBean)session.getAttribute("task");
		int count = 0;
		String resultText = "";
		
		//リストの取得
		List<CategoryBean> categoryList = (List<CategoryBean>)session.getAttribute("categoryList");
		List<StatusBean> statusList = (List<StatusBean>)session.getAttribute("statusList");
		List<UserBean> userList = (List<UserBean>)session.getAttribute("userList");
		
		TaskBean task = new TaskBean();
		
		//入力内容の取得、変数の用意
		int taskId = Integer.parseInt(request.getParameter("taskId"));//取得方法未定
		String taskName = request.getParameter("taskName");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = "";
		Date limitDate = Date.valueOf(request.getParameter("limitDate"));
		String userId = request.getParameter("userId");
		String userName = "";
		String statusCode = request.getParameter("statusCode");
		String statusName = "";
		String memo = request.getParameter("memo");
		
		//categoryId, statusCode, userIdの取得
		for(CategoryBean category : categoryList) {
			if(categoryId == category.getCategoryId()) {
				categoryName = category.getCategoryName();
			}
		}
		for(StatusBean status : statusList) {
			if(statusCode.equals(status.getStatusCode())) {
				statusName = status.getStatusName();
			}
		}
		for(UserBean user : userList) {
			if(userId.equals(user.getUserId())) {
				userName = user.getUserName();
			}
		}
		
		//TaskBean型へ格納
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
		
		request.setAttribute("task", task);
		
		//入力内容が同じな場合、処理をスキップ
		if(task.equals(oldTask)) {
			resultText = "以下のタスクは入力内容が同じです。";
			request.setAttribute("resultText", resultText);
			rd.forward(request, response);
		}
		
		try {
			count = taskEditDAO.editTask(task);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(count == 0) {
			resultText = "以下のタスク編集に失敗しました。";
		}
		if(count >0) {
			resultText = "以下のタスクを編集しました。";
		}
		
		request.setAttribute("resultText", resultText);
		
		rd.forward(request, response);
	}

}