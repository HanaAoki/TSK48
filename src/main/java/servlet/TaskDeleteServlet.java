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

import model.dao.TaskDeleteDAO;

/**
 * タスクの削除を行うサーブレット
 * 
 * @author Arino
 */
@WebServlet("/task-delete-servlet")
public class TaskDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
//		準備
		RequestDispatcher rd = request.getRequestDispatcher("task-delete-result.jsp");
		TaskDeleteDAO taskDeleteDAO = new TaskDeleteDAO();
		String[] taskId = (String[])session.getAttribute("taskId");
		System.out.println(taskId.length);
		int count = 0;
		String resultText = "";
		
//		タスク削除を実行
		try {
			count = taskDeleteDAO.deleteTask(taskId);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(count == 0) {
			resultText = "指定タスクを削除出来ませんでした。";
		}else {
			resultText = count + "件のタスクを削除しました。";
		}
		
		request.setAttribute("resultText", resultText);
		session.removeAttribute("taskId");
		rd.forward(request, response);
	}

}
