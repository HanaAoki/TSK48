package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.CommentBean;

public class CommentAddDAO {

	public int addTask(CommentBean commentBean) throws ClassNotFoundException, SQLException {
		int count = 0;

		String sql = "INSERT INTO t_comment (task_id, user_id, comment) VALUES (?, ?, ?)";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			// プレースホルダに入れる
			pstmt.setInt(1, commentBean.getTaskId());
			pstmt.setString(2, commentBean.getUserId());
			pstmt.setString(3, commentBean.getComment());

			count = pstmt.executeUpdate();
		}
		return count;

	}
}
