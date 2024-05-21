package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.StatusBean;

/**
 * m_statusの中身をリストに入れるDAO
 * @author 青木春菜
 */
public class StatusDAO {
	
	public List<StatusBean> selectStatus () throws ClassNotFoundException, SQLException {
//		返すリストの作成
		List<StatusBean> statusList = new ArrayList<StatusBean>();
		
//		sql文
		String sql = "SELECT status_code, status_name FROM m_status ORDER BY status_code";
		
//		db接続
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
//			実行
			ResultSet res = pstmt.executeQuery();
			
//			取り出してリストに入れる
			while (res.next()) {
//				statusbeanのインスタンス化
				StatusBean statusBean = new StatusBean();
				
				statusBean.setStatusCode(res.getString("status_code"));
				statusBean.setStatusName(res.getString("status_name"));
				
				statusList.add(statusBean);
			}
//			テスト
//			for (StatusBean statusBean : statusList) {
//				System.out.println(statusBean);
//			}
			
//			リストを返す
			return statusList;
		}
	}

}
