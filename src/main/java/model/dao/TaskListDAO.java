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
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t1.task_id, t1.task_name, t3.category_id, t3.category_name, t1.limit_date, t2.user_id, ");
		sql.append("t2.user_name, t4.status_code, t4.status_name, t1.memo, COUNT(t5.comment_id)");
		sql.append("FROM t_task t1 ");
		sql.append("JOIN m_user t2 ON t1.user_id = t2.user_id ");
		sql.append("JOIN m_category t3 ON t1.category_id = t3.category_id ");
		sql.append("JOIN m_status t4 ON t1.status_code = t4.status_code ");
		sql.append("LEFT JOIN t_comment t5 ON t1.task_id = t5.task_id ");
		sql.append("GROUP BY  t1.task_id, t1.task_name, t3.category_id, t3.category_name, t1.limit_date, t2.user_id, ");
		sql.append("t2.user_name, t4.status_code, t4.status_name, t1.memo");
		
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())){
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				TaskBean task = new TaskBean();
				int taskId = res.getInt("task_id");
				String taskName = res.getString("task_name");
				int categoryId =res.getInt("category_id");
				String categoryName = res.getString("category_name");
				Date limitDate = res.getDate("limit_date");
				String userId = res.getString("user_id");
				String userName = res.getString("user_name");
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");
				String memo = res.getString("memo");
				int commentNum = res.getInt("COUNT(t5.comment_id)");
				
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
				task.setCommentNum(commentNum);
				
				taskList.add(task);
			}
			
			return taskList;
		}
	}
	
}
