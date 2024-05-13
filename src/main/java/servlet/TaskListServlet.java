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

import model.dao.TaskListDAO;
import model.entity.TaskBean;

/**
 * タスク一覧表示へ遷移するサーブレットです。
 * 
 * @author Arino
 */
@WebServlet("/task-list-servlet")
public class TaskListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	/**
	 *タスクのリスト一覧を取得し、一覧表示画面へ送ります。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
		
		TaskListDAO taskListDao = new TaskListDAO();
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		
		try {
			taskList = taskListDao.selectAllTask();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("taskList", taskList);
		
		rd.forward(request, response);
	}

}
