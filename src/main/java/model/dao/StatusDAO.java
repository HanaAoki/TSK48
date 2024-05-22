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

	public List<StatusBean> selectStatus() throws ClassNotFoundException, SQLException {
		List<StatusBean> statusList = new ArrayList<StatusBean>();

		String sql = "SELECT status_code, status_name FROM m_status ORDER BY status_code";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet res = pstmt.executeQuery();

			// 取り出してリストに入れる
			while (res.next()) {
				StatusBean statusBean = new StatusBean();

				statusBean.setStatusCode(res.getString("status_code"));
				statusBean.setStatusName(res.getString("status_name"));

				statusList.add(statusBean);
			}
			return statusList;
		}
	}

}
