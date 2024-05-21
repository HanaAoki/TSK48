package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

/**
 * タスク登録をするためのDAO
 * @author 青木春菜
 */
public class TaskAddDAO {
	
	public int addTask(TaskBean taskBean) throws ClassNotFoundException, SQLException {
//		返す変数の設定
		int countAddTask = 0;
		
//		sql文
		String sql = "INSERT INTO t_task (task_name, category_id, limit_date, user_id, status_code, memo) VALUES (?, ?, ?, ?, ?, ?)";
		
//		db接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
//			プレースホルダに入れる
			pstmt.setString(1, taskBean.getTaskName());
			pstmt.setInt(2, taskBean.getCategoryId());
			pstmt.setDate(3, taskBean.getLimitDate());
			pstmt.setString(4, taskBean.getUserId());
			pstmt.setString(5, taskBean.getStatusCode());
			pstmt.setString(6, taskBean.getMemo());
			
//			実行
			int countAdd = pstmt.executeUpdate();
			
			return countAdd;
			
		}
	}

}
