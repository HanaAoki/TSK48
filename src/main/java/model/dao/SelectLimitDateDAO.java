package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * 期限の日を取り出すためのDAOです。
 * @author 青木春菜
 */
public class SelectLimitDateDAO {
	
	public Date selectLimitDate(String userId) throws ClassNotFoundException, SQLException {
		LocalDate today = LocalDate.now();
		Date limitDate = Date.valueOf(today);
		String sql = "SELECT limit_date FROM t_task WHERE user_id = ?";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, userId);
			
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				limitDate = res.getDate("limit_date");
			}
		}
		return limitDate;
	}

}
