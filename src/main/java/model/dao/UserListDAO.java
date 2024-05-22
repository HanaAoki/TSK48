package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

/**
 * ユーザーのリストを作るdao
 * @author 青木春菜
 */
public class UserListDAO {

	public List<UserBean> userList() throws ClassNotFoundException, SQLException {
		List<UserBean> userList = new ArrayList<UserBean>();

		String sql = "SELECT user_id, password, user_name FROM m_user";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			// 取り出してリストに入れる
			while (res.next()) {

				UserBean userBean = new UserBean();

				userBean.setUserName(sql);

				userBean.setUserId(res.getString("user_id"));
				userBean.setPassword(res.getString("password"));
				userBean.setUserName(res.getString("user_name"));

				userList.add(userBean);
			}

			return userList;
		}
	}

}
