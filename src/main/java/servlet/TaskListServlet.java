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
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
		HttpSession session = request.getSession();
		
		int pageNum = Integer.parseInt(request.getParameter("page"));
		int n = Integer.parseInt(request.getParameter("count"));
		
		int page = pageNum + n;
		if(page < 0) {
			page = 0;
		}
		
		TaskListDAO taskListDao = new TaskListDAO();
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		
		try {
			taskList = taskListDao.selectSomeTask(page);
			if(taskList.size() == 0) {
				taskList = taskListDao.selectSomeTask(--page);
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("page",page);
		session.setAttribute("taskList", taskList);
		
		rd.forward(request, response);
	}

	
	/**
	 *タスクのリスト一覧を取得し、一覧表示画面へ送ります。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
		HttpSession session = request.getSession();
		
		//準備
		TaskListDAO taskListDao = new TaskListDAO();
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		
		//タスク一覧の取得、格納
		try {
			taskList = taskListDao.selectSomeTask();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("taskList", taskList);
		
		rd.forward(request, response);
	}

}
