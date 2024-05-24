package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskListDAO;
import model.dao.UserListDAO;
import model.entity.ScheduleBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * @author Arino
 */
@WebServlet(name = "TaskCalendarServlet", urlPatterns = { "/task-calendar-servlet" })
public class TaskCalenderServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("task-calendar.jsp");
		
		UserListDAO userListDAO = new UserListDAO();
		TaskListDAO taskListDAO = new TaskListDAO();
		List<UserBean> userList = new ArrayList<UserBean>();
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		List<ScheduleBean> scheduleList = new ArrayList<ScheduleBean>();
		
		int dayShift = Integer.parseInt(request.getParameter("dayShift"));
		String dateStr = request.getParameter("date");
		
		Date baseDateTime = Date.valueOf(dateStr);
		LocalDate shiftDate = baseDateTime.toLocalDate();
		shiftDate = shiftDate.plusDays(dayShift);
		
		try {
			userList = userListDAO.userList();
			taskList = taskListDAO.selectAllTask();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(UserBean user : userList) {
			ScheduleBean scedule = new ScheduleBean();
			scedule.makeSchedule(user.getUserName(), taskList, shiftDate);
			scheduleList.add(scedule);
		}
		
		request.setAttribute("scheduleList",scheduleList);
		request.setAttribute("userList",userList);
		request.setAttribute("date", shiftDate);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("task-calendar.jsp");
		
		UserListDAO userListDAO = new UserListDAO();
		TaskListDAO taskListDAO = new TaskListDAO();
		List<UserBean> userList = new ArrayList<UserBean>();
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		List<ScheduleBean> scheduleList = new ArrayList<ScheduleBean>();
		
		LocalDate currentDate = LocalDate.now();
		
		try {
			userList = userListDAO.userList();
			taskList = taskListDAO.selectAllTask();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(UserBean user : userList) {
			ScheduleBean scedule = new ScheduleBean();
			scedule.makeSchedule(user.getUserName(), taskList, currentDate);
			scheduleList.add(scedule);
		}
		
		request.setAttribute("scheduleList",scheduleList);
		request.setAttribute("userList",userList);
		request.setAttribute("date", currentDate);
		
		rd.forward(request, response);
	}

}
