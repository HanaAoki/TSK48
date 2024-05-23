package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDeleteDAO {
	
	public int deleteTask(String[] taskId)throws ClassNotFoundException, SQLException{
		int count = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM t_task WHERE task_id = ?");
		for(int i = 1; i < taskId.length; i++) {
			sql.append(" OR task_id = ?");
		}
		try(Connection con = ConnectionManager.getConnection()) {
				
			try (PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

				con.setAutoCommit(false);

				deleteComment(taskId);

				for (int i = 0; i < taskId.length; i++) {
					pstmt.setString(i + 1, taskId[i]);
				}

				count = pstmt.executeUpdate();

				con.commit();

			} catch (SQLException e) {
				con.rollback();
				System.out.println("ロールバックしました");
				e.printStackTrace();
			}
		
		}
		
		return count;
	}
	
	public int deleteComment(String[] taskId)throws SQLException, ClassNotFoundException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM t_task WHERE task_id = ?");
		for(int i = 1; i < taskId.length; i++) {
			sql.append(" OR task_id = ?");
		}
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql.toString())){
			
			for(int i = 0; i < taskId.length; i++) {
				pstmt.setString(i+1, taskId[i]);
			}
			
			return pstmt.executeUpdate();
		}
	}
}
