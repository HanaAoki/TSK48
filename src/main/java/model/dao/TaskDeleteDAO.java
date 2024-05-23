package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDeleteDAO {
	
	public int deleteTask(String[] taskId)throws ClassNotFoundException, SQLException{
		int count = 0;
		String sql = "DELETE FROM t_task WHERE task_id = ?";
		for(int i = 1; i < taskId.length; i++) {
			sql += " OR task_id = ?";
		}
		try(Connection con = ConnectionManager.getConnection()) {
				
			try(PreparedStatement pstmt = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			
			deleteComment(taskId);
			
			for(int i = 0; i < taskId.length; i++) {
				pstmt.setString(i+1, taskId[i]);
			}
			
			con.commit();
			
			 count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
		}
		
		}
		
		return count;
	}
	
	public int deleteComment(String[] taskId)throws SQLException, ClassNotFoundException{
		String sql = "DELETE FROM t_comment WHERE task_id = ?";
		for(int i = 1; i < taskId.length; i++) {
			sql += " OR task_id = ?";
		}
		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			for(int i = 0; i < taskId.length; i++) {
				pstmt.setString(i+1, taskId[i]);
			}
			
			return pstmt.executeUpdate();
		}
	}
}
