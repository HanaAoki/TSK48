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
				+ "SET item_name = ?, category_code = ?, price = ? "
				+ "item_name = ?, category_code = ?, price = ? "
				+ "WHERE item_code = ? ";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			String taskname = confirm.getTaskName();
			int categoryid = confirm.getCategoryId();
			Date limitdate = confirm.getLimitDate();
			String price = confirm.getStatusId();
			int taskid = confirm.getTaskId();

			pstmt.setString(1, taskname);
			pstmt.setInt(2, categoryid);
			pstmt.setString(3, taskname);
			pstmt.setString(4, taskname);
			pstmt.setString(5, taskname);
			pstmt.setInt(6, price);
			pstmt.setInt(7, taskid);

			count = pstmt.executeUpdate();
		}

		return count;

	}
}
