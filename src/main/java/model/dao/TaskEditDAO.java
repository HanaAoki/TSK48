package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;


public class TaskEditDAO {

	public int editTask(TaskBean confirm)
			throws SQLException, ClassNotFoundException {

		int count = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE t_task SET task_name = ?, category_id = ?, limit_date = ?, ");
		sql.append("status_code = ?, user_id = ?, memo = ? WHERE task_id = ? ");

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

			String taskname = confirm.getTaskName();
			int categoryid = confirm.getCategoryId();
			Date limitdate = confirm.getLimitDate();
			String statuscode = confirm.getStatusCode();
			String userid =  confirm.getUserId();
			String memo = confirm.getMemo();
			int taskid = confirm.getTaskId();
			
			pstmt.setString(1, taskname);
			pstmt.setInt(2, categoryid);
			pstmt.setDate(3, limitdate);
			pstmt.setString(4, statuscode);
			pstmt.setString(5, userid);
			pstmt.setString(6, memo);
			pstmt.setInt(7, taskid);

			count = pstmt.executeUpdate();
		}

		return count;

	}
}
