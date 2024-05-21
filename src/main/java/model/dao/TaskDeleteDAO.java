package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDeleteDAO {
	
	public int deleteTask(String[] taskId)throws SQLException, ClassNotFoundException{
		String sql = "DELETE FROM t_task WHERE task_id = ?";
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
