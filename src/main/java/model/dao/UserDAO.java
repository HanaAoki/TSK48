package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;


/**
 * ログインを行うメソッドです
 * @author 重松
 */
public class UserDAO {
	
	/**
	 * @param userId
	 * @param password
	 * @return Ub
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserBean login(String userId, String password) throws SQLException, ClassNotFoundException {

		String sql = "SELECT * FROM m_user WHERE user_id = ? AND password = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			String UserId = userId;
			String passWord = password;

			pstmt.setString(1, UserId);
			pstmt.setString(2, passWord);

			ResultSet res = pstmt.executeQuery();

			UserBean Ub = new UserBean();

			if (res.next()) {
				String userid = res.getString("user_id");
				String pass = res.getString("password");
				String username = res.getString("user_name");

				Ub.setUserId(userid);
				Ub.setPassword(pass);
				Ub.setUserName(username);
			} else {
				Ub = null;
			}
			return Ub;
		}

	}
}
