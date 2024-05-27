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
	
	public LocalDate selectLimitDate(String userId) throws ClassNotFoundException, SQLException {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		Date yesterdayDate = Date.valueOf(yesterday);
		LocalDate limit = today.plusDays(5);
		String sql = "SELECT limit_date FROM t_task WHERE limit_date IS NOT NULL AND limit_date > ? AND user_id = ? ORDER BY limit_date ASC";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setDate(1, yesterdayDate);
			pstmt.setString(2, userId);
			
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				Date date = res.getDate("limit_date");
				limit = date.toLocalDate();
				if (limit.isAfter(yesterday)) {
					return limit;
				}
				
			}
		}
		return limit;
	}

}
