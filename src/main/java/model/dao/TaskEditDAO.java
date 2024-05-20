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

		String sql = "UPDATE t_task "
				+ "SET task_name = ?, category_id = ?, limit_date = ? "
				+ "status_code = ?, userid = ?, memo = ? "
				+ "WHERE task_id = ? ";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			String taskname = confirm.getTaskName();
			int categoryid = confirm.getCategoryId();
			Date limitdate = confirm.getLimitDate();
			String userid =  confirm.getUserId();
			String statuscode = confirm.getStatusCode();
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
