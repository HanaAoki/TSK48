package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

/**
 * タスクの一覧取得をするDAOです。
 * 
 * @author Arino
 */
public class TaskListDAO {
	
	/**
	 * タスク一覧のリストを取得するメソッド
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<TaskBean> selectAllTask() throws SQLException, ClassNotFoundException{
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "SELECT t1.task_id, t1.task_name, t3.category_id, t3.category_name, t1.limit_date, t2.user_id, t2.user_name, t4.status_code, t4.status_name, t1.memo "
				+ "FROM t_task AS t1 "
				+ "JOIN m_user AS t2 ON t1.user_id = t2.user_id "
				+ "JOIN m_category t3 ON t1.category_id = t3.category_id "
				+ "JOIN m_status AS t4 ON t1.status_code = t4.status_code";
		
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				TaskBean task = new TaskBean();
				int taskId = res.getInt("task_id");
				String taskName = res.getString("task_name");
				int categoryId =res.getInt("category_id");
				String categoryName = res.getString("category_name");
				Date limitDate = res.getDate("limit_date");
				String userName = res.getString("user_name");
				String statusName = res.getString("status_name");
				String memo = res.getString("memo");
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setCategoryName(categoryName);
				task.setLimitDate(limitDate);
				task.setUserName(userName);
				task.setStatusName(statusName);
				task.setMemo(memo);
				
				taskList.add(task);
			}
			
			return taskList;
		}
	}
	
}
