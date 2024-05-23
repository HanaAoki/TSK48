package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskListDAO;
import model.dao.UserListDAO;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * @author Arino
 */
@WebServlet(name = "TaskCalendarServlet", urlPatterns = { "/task-calendar-servlet" })
public class TaskCalenderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserListDAO userListDAO = new UserListDAO();
		TaskListDAO taskListDAO = new TaskListDAO();
		
		List<UserBean> userList = new ArrayList<UserBean>();
		List<TaskBean> taskList = new ArrayList<TaskBean>();
	}

}
