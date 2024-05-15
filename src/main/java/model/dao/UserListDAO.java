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
//		返すリストの作成
		List<UserBean> userList = new ArrayList<UserBean>();
		
//		sql文
		String sql = "SELECT user_id, password, user_name FROM m_user";
		
//		db接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
//			実行
			ResultSet res = pstmt.executeQuery();
			
//			取り出してリストに入れる
			while (res.next()) {
				
//				categorybeanのインスタンス化
				UserBean userBean = new UserBean();
				
				userBean.setUserName(sql);
				
				userBean.setUserId(res.getString("user_id"));
				userBean.setPassword(res.getString("password"));
				userBean.setUserName(res.getString("user_name"));
				
				userList.add(userBean);
			}
			
//			テスト
//			for (CategoryBean categoryBean : categoryList) {
//				System.out.println(categoryBean);
//			}
			
//			リストを返す
			return userList;
		}
	}

}
